package com.maijia.rc.activity;

import android.app.Activity;

import java.util.Stack;

/**
 * Activity管理栈 保存activity的实例，当程序退出时情况activity
 *
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ActivityStack {
    /**
     * 栈对象,用于存放Activity对象，以便于管理
     */
    private static Stack<Activity> mStack;

    /**
     * 单例对象
     */
    private static ActivityStack mActivityStack;

    /**
     * 私有化构造器，不让在类的外部实例化该对象，保证栈的单例性
     */
    private ActivityStack() {

    }

    /**
     * 获取单例对象
     *
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static ActivityStack getInstance() {
        if (mActivityStack == null) {
            mActivityStack = new ActivityStack();
        }
        return mActivityStack;
    }

    /**
     * 获取管理栈集合
     */
    public Stack<Activity> getActivitiesList() {
        return mStack;
    }

    /**
     * 弹出Activity
     *
     * @param activity
     * @param isFinish 是否手动finish掉activity
     * @see [类、类#方法、类#成员]
     */
    public void popActivity(Activity activity, boolean isFinish) {
        if (activity != null) {
            if (isFinish) {
                activity.finish();
            }
            mStack.remove(activity);
        }
    }

    /**
     * 销毁除指定LoginActivity外的其他activity
     *
     * @param
     * @see [类、类#方法、类#成员]
     */
    public void popOtherActivity() {
//        if (mStack != null) {
//            int index = mStack.size();
//            for (int i = 0; i < index; i++) {
//                Activity currentActivity = currentActivity();
////             当前Activity不为空，并且不属于登录Activity才弹出栈
//                if (currentActivity != null && !(currentActivity instanceof LoginActivity)) {
//                    popActivity(currentActivity, true);
//                }
//            }
//        }

        // 循环遍历该Stack中所有的Activity,因为最后只剩登录，所以>1就可以了，少一次循环判断
        while (mStack != null && mStack.size() > 1) {
//            Log.e("ActivityStack", "mStack.size()==" + mStack.size());
            Activity currentActivity = currentActivity();
//             当前Activity不为空，并且不属于登录Activity才弹出栈
            if (currentActivity != null) {
//            if (currentActivity != null && !(currentActivity instanceof LoginActivity)) {
                popActivity(currentActivity, true);
            }
        }
    }

    /**
     * 获取栈顶的Activity
     *
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Activity currentActivity() {
        if (!mStack.isEmpty()) {
            // 该方法Returns the last element in this vector.也就是说栈顶的
            return (Activity) mStack.lastElement();
        } else {
            return null;
        }
    }

    /**
     * 获取栈的Acitivity
     *
     * @param dept 索引（倒叙形式）
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Activity getNextActivity(int dept) {
        // 反序返回指定索引号的Activity实例，但不做弹出栈操作
        if (dept <= mStack.size()) {
            return mStack.get(dept);
        } else {
            return null;
        }
    }

    /**
     * Activity入栈
     *
     * @param activity
     * @see [类、类#方法、类#成员]
     */
    public void pushActivity(Activity activity) {
        if (mStack == null) {
            mStack = new Stack<Activity>();
        }
        mStack.add(activity);
    }

    /**
     * 清空Activity栈
     *
     * @see [类、类#方法、类#成员]
     */
    public void popAllActivity() {
        while (mStack != null && mStack.size() > 0) {
            Activity activity = currentActivity();
            if (activity != null) {
                popActivity(activity, true);
            }
        }
    }

    /**
     * 获取指定的Acitivity
     *
     * @param （倒叙形式）
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Activity getMainActivity() {
        Activity activity = null;
        if (!mStack.isEmpty() && mStack.size() > 0) {
            for (int i = 0; i < mStack.size(); i++) {
//                if (getNextActivity(i) instanceof MainActivity) {
//                    activity = getNextActivity(i);
//                    break;
//                }

            }
        }
        return activity;
    }

    /**
     * 获取指定的Acitivity
     *
     * @param （倒叙形式）
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Activity getFoundCampaignDetailActivity() {
        Activity activity = null;
        if (!mStack.isEmpty() && mStack.size() > 0) {
            for (int i = 0; i < mStack.size(); i++) {
//                if (getNextActivity(i) instanceof FoundCampaignDetailActivity)
//                {
//                    activity = getNextActivity(i);
//                    break;
//                }

            }
        }
        return activity;
    }

}