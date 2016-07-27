package cn.wyx.android.swipeback.swipe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * 滑动关闭
 */
public class SwipeBackActivity extends AppCompatActivity implements SwipeBackLayout.SwipeBackListener {

    public static final String TAG = "SwipeBackActivity";

    private static final String KEY_SCREENSHOT = "screenshot";

    /**
     * 是否可以右滑关闭Activity
     */
    protected boolean canSwipeBack = true;
    private SwipeBackLayout container;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        if (canSwipeBack){
            addSwipeFinish();
        }
    }

    /**
     *
     */
    protected void addSwipeFinish() {
        ViewGroup container = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
        if (container != null) {
            ViewGroup firstChild = (ViewGroup) container.getChildAt(0);
            container.removeAllViews();
            container.addView(getContainer(firstChild));
        }
    }

    private View getContainer(View firstChild) {

        container = new SwipeBackLayout(this);
        //读取A页面发送过来的截图
        String screenshotPath = getIntent().getStringExtra(KEY_SCREENSHOT);
        if (screenshotPath != null) {
            File file = new File(screenshotPath);
            Log.i(TAG, screenshotPath);
            Bitmap screenshot = BitmapFactory.decodeFile(screenshotPath);
            container.setLastScreenshot(screenshot);
            file.delete();
        } else {
            Log.i(TAG, "screenshot is null");
        }

        container.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        container.setOnSwipeBackListener(this);
        container.addView(firstChild);
        return container;
    }

    public void setDragEdge(SwipeBackLayout.DragEdge dragEdge) {
        container.setDragEdge(dragEdge);
    }

    public SwipeBackLayout getSwipeBackLayout() {
        return container;
    }

    @Override
    public void onViewPositionChanged(float fractionAnchor, float fractionScreen) {
        //getWindow().getDecorView().setAlpha(1 - fractionScreen);
        //rshadowLayout.requestLayout();
        //container.setTranslationX(fractionScreen * getResources().getDisplayMetrics().widthPixels);

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        addScreenshotBundle(intent);
        super.startActivityForResult(intent, requestCode, options);
    }

    /**
     * 将A页面的截图保存起来,然后在B页面使用
     *
     * @param intent
     */
    private void addScreenshotBundle(Intent intent) {
        ViewGroup content = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
        content.destroyDrawingCache();//释放绘图资源所占缓存,不然可能导致获取到的view视图是错误的
        content.buildDrawingCache();
        content.setDrawingCacheEnabled(true);
        Bitmap bitmap = content.getDrawingCache();

        String screenshotPath = getCacheDir() + "/screenshot.jpeg";
        try {
            File file = new File(screenshotPath);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
            boolean success = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            stream.flush();
            stream.close();
            Toast.makeText(this, success + "", Toast.LENGTH_LONG).show();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        intent.putExtra(KEY_SCREENSHOT, screenshotPath);
    }


}
