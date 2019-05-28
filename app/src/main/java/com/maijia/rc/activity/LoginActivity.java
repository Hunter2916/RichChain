package com.maijia.rc.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;

import com.maijia.rc.R;
import com.maijia.rc.databinding.ActivityLoginBinding;
import com.maijia.rc.di.component.DaggerUserComponent;
import com.maijia.rc.di.module.ActivityModule;
import com.maijia.rc.di.module.UserModule;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends BaseActivity {
    private Context context;
    ActivityLoginBinding binding;
    private ArrayAdapter adapter;
    private List<String> nickName = Arrays.asList("请选择昵称", "张三", "李四", "王五", "小孔", "小李");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().pushActivity(this);
        context = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        //将可选内容与ArrayAdapter连接起来
//        adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, nickName);
//        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//        binding.loginNickName.setAdapter(adapter);

        setStatusBarColor();
        initInject();
        initClick();
        setRegisterTextColor();
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void setRegisterTextColor() {
        String str = "还没有账号？赶快<font color='#1C8FFD'>注册</font>吧";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            binding.registerMember.setText(Html.fromHtml(str, Html.FROM_HTML_MODE_LEGACY));
        } else {
            binding.registerMember.setText(Html.fromHtml(str));
        }

    }

    private void initClick() {
        binding.forgetPad.setOnClickListener(this);
        binding.registerMember.setOnClickListener(this);
        binding.findNickName.setOnClickListener(this);
    }

    private void initInject() {
        DaggerUserComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(getApplicationComponent())
                .userModule(new UserModule())
                .build()
                .inject(this);
    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forget_pad:
                ForgetPadActivity.startAction(context);
                break;
            case R.id.register_member:
                RegisterActivity.startAction(context);
                break;
            case R.id.find_nick_name:
                FindNickNameActivity.startAction(context);
                break;
        }

    }
}
