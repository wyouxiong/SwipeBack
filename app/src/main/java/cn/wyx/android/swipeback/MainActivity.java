package cn.wyx.android.swipeback;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.wyx.android.swipeback.swipe.SwipeBackActivity;

public class MainActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        canSwipeBack = false;
        setContentView(R.layout.activity_main);
    }


    public void normal(View view) {
        startActivity(new Intent(this, NormalActivity.class));
    }

    public void fullscreen(View view) {
        startActivity(new Intent(this, FullscreenActivity.class));
    }

    public void scrolling(View view) {
        startActivity(new Intent(this, ScrollingActivity.class));
    }
}
