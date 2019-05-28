package com.maijia.rc.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.maijia.rc.fragment.BaseFragment;

import java.util.HashMap;
import java.util.Map;


/**
 * fragment管理类
 *
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class FragmentManageActivity extends BaseActivity {
    private static final String TAG = "FragmentManageActivity";

    /**
     * 当前显示的Fragment引用
     */
    protected BaseFragment currentFragment = null;

    /**
     * 存放fragment的map
     */
    protected Map<String, BaseFragment> mapFragment = new HashMap<String, BaseFragment>();

    /**
     * [一句话功能简述]<BR>
     * [功能详细描述]
     *
     * @param arg0
     * @see android.support.v4.app.FragmentActivity#onCreate(Bundle)
     */
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
    }

    /**
     * [请求下个fragment]<BR>
     * [功能详细描述]
     */
    public void nextFragment(String key) {
        showFragment(getFragment(key), key);
    }

    /**
     * [一句话功能简述]<BR>
     * [功能详细描述]
     *
     * @param fragment
     */
    private void showFragment(BaseFragment fragment, String key) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }
        if (fragment.isAddedToActivity()) {
            transaction.show(fragment);
        } else {
            // 如果fragment没有添加到Activity中,就将传进来的fragment添加进来
            mapFragment.put(key, fragment);
            transaction.add(getContendId(), fragment);
            // 将标记值为true,下次根据key找到fragment直接显示就可以了
            fragment.setAddedToActivity(true);
        }
        // 此处添加到back栈的代码需要进行测试
        // transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
        // 提交事务后，将传入的fragment变为当前的fragment
        currentFragment = fragment;
    }

    /**
     * [获取用于显示fragment的layout的Id,抽象的，子类必须重写]<BR>
     * [功能详细描述]
     *
     * @return
     */
    protected abstract int getContendId();

    // 通过key获取fragment对象,没有就创建
    public BaseFragment getFragment(String key) {
        // 如果fragment不为空
        if (mapFragment != null) {
            // 如果有直接返回，否则创建一个新的fragment
            if (mapFragment.containsKey(key)) {
                return mapFragment.get(key);
            } else {
                // 上面方法showFragment()前提是有fragment,然后判断添加到Activity中
                return createBaseFragment(key);
            }
        }
        return null;
    }

    /**
     * [实例化DetailBaseFragment，返回实例,由子类去写具体的fragment]<BR>
     * [功能详细描述]
     *
     * @param
     * @return
     */
    protected abstract BaseFragment createBaseFragment(String key);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 清空map缓存
        if (mapFragment != null && mapFragment.size() > 0) {
            mapFragment.clear();
        }
    }
}