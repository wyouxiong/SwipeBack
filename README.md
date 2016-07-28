# Summary
支持手势滑动关闭Activity，支持上下左右四个方向。在滑动关闭时，会像微信一样显示上一个页面和一条阴影

# Screenshot


# Usage
## 1 在Activity的theme必须指定以下两项：

```
  <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
       
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
        <!-- 必须得设置下面两个属性，否则看不到上一个页面-->
        <item name="android:windowIsTranslucent">true</item>
        <item name="android:windowBackground">@android:color/transparent</item>
  </style>
```
## 2 继承自SwipeBackActivity

```
  public class NormalActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        setDragEdge(SwipeBackLayout.DragEdge.BOTTOM);
    }
}
```

## 3. 如果不想使用手势关闭，那么可以在当前Activity做如下设置

```
  public class MainActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //关闭手势
        canSwipeBack = false;
        setContentView(R.layout.activity_main);
    }
  }
```




