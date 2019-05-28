package com.maijia.rc.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.maijia.rc.R;
import com.maijia.rc.app.MyApplication;
import com.maijia.rc.di.component.ApplicationComponent;
import com.maijia.rc.di.module.ActivityModule;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by zhaoliang on 2018/6/14 0014
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((MyApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    //显示短的提示
    public void showToastShort(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    //显示长提示
    public void showToastLong(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    /**
     * 设置沉浸式状态栏
     */
    public void setStatusBarColor() {
        // 4.4及以上版本开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);

//        // 自定义颜色
        if (this instanceof MainActivity) {
            tintManager.setTintColor(getResources().getColor(R.color.blue_bg));
        }
        else if (this instanceof LoginActivity) {
            tintManager.setTintColor(getResources().getColor(R.color.white_snow));
        }
        else {
//            tintManager.setTintResource(R.drawable.bg_home_gradient);
            tintManager.setTintResource(R.color.blue_bg);
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}
