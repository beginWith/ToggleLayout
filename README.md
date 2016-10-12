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
