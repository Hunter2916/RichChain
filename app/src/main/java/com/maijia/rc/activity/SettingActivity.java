package com.maijia.rc.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.maijia.rc.R;
import com.maijia.rc.databinding.ActivitySettingBinding;

public class SettingActivity extends BaseActivity {
    ActivitySettingBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        ActivityStack.getInstance().pushActivity(this);
        setStatusBarColor();
        binding.titlebar.title.setText("设置");
        initClick();
    }

    private void initClick() {

        binding.titlebar.back.setOnClickListener(this);
        binding.mineInformation.setOnClickListener(this);
        binding.exchangeSet.setOnClickListener(this);
        binding.changeLoginPassword.setOnClickListener(this);
        binding.changeTradePassword.setOnClickListener(this);
        binding.forgetTradePsd.setOnClickListener(this);
        binding.aboutUs.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            //个人资料
            case R.id.mine_information:
                MineInformationActivity.startAction(context);
                break;
            //兑换设置
            case R.id.exchange_set:
                ExechangeSettingActivity.startAction(context);
                break;
            //修改登录密码
            case R.id.change_login_password:
                AlterPasswordActivity.startAction(context);
                break;
            //修改交易密码
            case R.id.change_trade_password:
                AlterTradePasswordActivity.startAction(context);
                break;
            //忘记交易密码
            case R.id.forget_trade_psd:
                FindTradePsdActivity.startAction(context);
                break;
            //关于我们
            case R.id.about_us:
                break;
        }
    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }
}
