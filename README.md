# ToggleLayout
ToggleLayout是一个可以切换的viewgroup.

![image](https://github.com/githubwing/ToggleLayout/raw/master/perview.gif)


## 使用方法：
ToggleLayout是一个有且仅有2个子view的Layout.

只需要在布局中加入两个View即可.

在xml中加入布局:
```
<com.wingsofts.togglelayout.ToggleLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/logsignView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/bg"
    >
<View />
<View />
</com.wingsofts.togglelayout.ToggleLayout>

```

可以添加onSubmitListener()来监听完成事件~  这里是对整个View进行监听 尚不完善。

complete()函数可以执行完成动画。

中间动画过度不流畅。。暂时待解决，没啥好想法。。



## 实现方式
是通过自定义viewgroup实现的，其实是简单的view基础动画，关键在于
View的bringToFront()函数，该函数可以将viewgroup中view放到绘制最上层
故在点击事件发生时同时出发view动画，并且调用该函数，即可将两view层级变换。
