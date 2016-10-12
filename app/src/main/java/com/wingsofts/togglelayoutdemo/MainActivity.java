package com.wingsofts.togglelayoutdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.wingsofts.togglelayoutdemo.R;
import com.wingsofts.togglelayout.ToggleLayout;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final ToggleLayout toggleLayout = (ToggleLayout) findViewById(R.id.logsignView);
    toggleLayout.setOnSubmitListener(new ToggleLayout.OnSubmitListener() {
      @Override public void onSubmit(View v) {
        if(v.getTag().equals("sign")) {
          Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
          toggleLayout.complete();

        }else {
          Toast.makeText(MainActivity.this, "login clicked", Toast.LENGTH_SHORT).show();
        }
      }
    });

    toggleLayout.setDuration(800);
  }
}
