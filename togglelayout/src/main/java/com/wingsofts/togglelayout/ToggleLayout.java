package com.wingsofts.togglelayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by wing on 10/12/16.
 */

public class ToggleLayout extends ViewGroup {

  private View mSignView;
  private View mLoginView;
  private View mCurrentFrontView;
  private View mCurrentBackView;
  //frontView's X
  private int mFrontViewX;

  private int mFrontViewY;
  private int mBackOffset = 100;

  private OnSubmitListener mListener;

  public void setOnSubmitListener(OnSubmitListener listener) {
    mListener = listener;
  }

  public interface OnSubmitListener {
    void onSubmit(View v);
  }

  public ToggleLayout(Context context) {
    super(context);
  }

  public ToggleLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public ToggleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int count = getChildCount();
    for (int i = 0; i < count; i++) {
      measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
      View view = getChildAt(i);

      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {

    if (changed) {
      View signView = getChildAt(0);
      mSignView = mCurrentFrontView = signView;
      View loginView = getChildAt(1);
      mLoginView = mCurrentBackView = loginView;

      mCurrentBackView.setRotation(270);

      mSignView.setOnClickListener(clickListener);
      mLoginView.setOnClickListener(clickListener);
    }
    if (getChildCount() != 2) {
      throw new IllegalStateException("LogSignView must have two child");
    }

    mFrontViewY = (getMeasuredHeight() - mCurrentFrontView.getMeasuredHeight()) / 2;

    mFrontViewX = (getMeasuredWidth() - mCurrentFrontView.getMeasuredWidth()) / 2;

    mCurrentFrontView.layout(mFrontViewX, mFrontViewY,
        mFrontViewX + mCurrentFrontView.getMeasuredWidth(),
        mFrontViewY + mCurrentFrontView.getMeasuredHeight());

    mCurrentBackView.layout(mFrontViewX + mBackOffset, mFrontViewY,
        mCurrentBackView.getMeasuredWidth() + mFrontViewX + mBackOffset,
        mCurrentBackView.getMeasuredHeight() + mFrontViewY);

    mCurrentFrontView.bringToFront();
  }

  private long mDuration = 1000;
  OnClickListener clickListener = new OnClickListener() {
    @Override public void onClick(View v) {
      if (mCurrentFrontView == v) {
        if (mListener != null) {
          mListener.onSubmit(v);
        }
        return;
      }
      if (mCurrentFrontView != mLoginView) {
        mCurrentFrontView = mLoginView;
        mCurrentBackView = mSignView;
      } else if (mCurrentFrontView != mSignView) {
        mCurrentFrontView = mSignView;
        mCurrentBackView = mLoginView;
      }



      Interpolator interpolator = new Interpolator() {
        @Override public float getInterpolation(float input) {
          float factor = 0.4f;
          return (float) (Math.pow(2, -10 * input) * Math.sin(
              (input - factor / 4) * (2 * Math.PI) / factor) + 1);
        }
      };

      setBackViewAni(interpolator);

      setFrontViewAni(interpolator);
    }

    private void setBackViewAni(Interpolator interpolator) {
      RotateAnimation ra = new RotateAnimation(0f, -75f, 0f, 0f);
      ra.setDuration(mDuration);
      ra.setInterpolator(interpolator);
      ra.setAnimationListener(new Animation.AnimationListener() {
        @Override public void onAnimationStart(Animation animation) {

        }

        @Override public void onAnimationEnd(Animation animation) {

          mCurrentBackView.setRotation(0);
          mCurrentFrontView.setRotation(0);

          mCurrentBackView.setRotation(270);
          TranslateAnimation ta = new TranslateAnimation(-45, 0,
              mCurrentFrontView.getY() - mCurrentFrontView.getWidth() * 2, 0);
          ta.setDuration(mDuration/2);
          mCurrentBackView.startAnimation(ta);

          requestLayout();
        }

        @Override public void onAnimationRepeat(Animation animation) {

        }
      });

      mCurrentBackView.startAnimation(ra);
    }

    private void setFrontViewAni(Interpolator interpolator) {
      RotateAnimation raFront = new RotateAnimation(0f, 75f, 0f, 0f);
      raFront.setDuration(mDuration);
      raFront.setInterpolator(interpolator);
      raFront.setAnimationListener(new Animation.AnimationListener() {
        @Override public void onAnimationStart(Animation animation) {

        }

        @Override public void onAnimationEnd(Animation animation) {


          TranslateAnimation ta =
              new TranslateAnimation(0 - mCurrentFrontView.getWidth() + 50, 0, -50, 0);
          ta.setDuration(mDuration/2);
          mCurrentFrontView.startAnimation(ta);
        }

        @Override public void onAnimationRepeat(Animation animation) {

        }
      });
      mCurrentFrontView.startAnimation(raFront);
    }
  };

  public void setDuration(long mDuration) {
    this.mDuration = mDuration;
  }

  public void complete(){
    TranslateAnimation ta = new TranslateAnimation(0,0,0,2000);
    ta.setDuration(1000);
    mCurrentBackView.startAnimation(ta);
    mCurrentFrontView.startAnimation(ta);
    mCurrentFrontView.setVisibility(INVISIBLE);
    mCurrentBackView.setVisibility(INVISIBLE);
  }
}
