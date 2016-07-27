package cn.wyx.android.swipeback;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.wyx.android.swipeback.swipe.SwipeBackActivity;
import cn.wyx.android.swipeback.swipe.SwipeBackLayout;

public class NormalActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        setDragEdge(SwipeBackLayout.DragEdge.BOTTOM);
    }
}
