package com.maijia.rc.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * fragment基类,所有的fragment都必须继承此基类 ,不带分页和下拉刷新的也可以继承此基类
 *
 * @author n003345
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    /**
     * 该Fragment是否被添加到Activity中，避免重复添加
     */
    private boolean addedToActivity = false;
    private DisplayMetrics metrics;
    /**
     * 数据是否初始化完成,在需要初始化的类里重新赋值此变量
     */
    public boolean dataInited = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        WindowManager manager = getActivity().getWindowManager();
        metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        super.onCreate(savedInstanceState);
    }

    /**
     * [初始化界面，子类必须调用super]<BR>
     * [功能详细描述] 子类继承此类，就会有该方法，然后执行里面的方法
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     * @see Fragment#onCreateView(LayoutInflater, ViewGroup,
     * Bundle)
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return null;
    }

    /**
     * 获取手机屏幕宽度
     */
    public int getScreenWidth() {
        return metrics.widthPixels;
    }

    /**
     * @return the addedToActivity
     */
    public boolean isAddedToActivity() {
        return addedToActivity;
    }

    /**
     * @param addedToActivity the addedToActivity to set
     */
    public void setAddedToActivity(boolean addedToActivity) {
        this.addedToActivity = addedToActivity;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}