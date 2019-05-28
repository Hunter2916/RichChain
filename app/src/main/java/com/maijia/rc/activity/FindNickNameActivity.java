package com.maijia.rc.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.maijia.rc.R;
import com.maijia.rc.databinding.ActivityFindNickNameBinding;
import com.maijia.rc.di.component.DaggerUserComponent;
import com.maijia.rc.di.module.ActivityModule;
import com.maijia.rc.di.module.UserModule;

/**
 * 查找用户名
 */
public class FindNickNameActivity extends BaseActivity {
    ActivityFindNickNameBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().pushActivity(this);
        context = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_find_nick_name);
        binding.titlebar.title.setText("找回用户名");
        setStatusBarColor();
        initInject();
        initClick();
    }

    private void initClick() {
        binding.titlebar.back.setOnClickListener(this);
        binding.next.setOnClickListener(this);

    }

    private void initInject() {
        DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .userModule(new UserModule())
                .build()
                .inject(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.next:
                FindNickNameDetailActivity.startAction(context);
                break;
        }
    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, FindNickNameActivity.class);
        context.startActivity(intent);
    }
}
