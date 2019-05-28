package com.maijia.rc.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.maijia.rc.R;
import com.maijia.rc.activity.exechangeset.ALiPayActivity;
import com.maijia.rc.activity.exechangeset.BankCardActivity;
import com.maijia.rc.activity.exechangeset.C1IdentificationActivity;
import com.maijia.rc.activity.exechangeset.C2IdentificationActivity;
import com.maijia.rc.activity.exechangeset.OtherPayStyleActivity;
import com.maijia.rc.activity.exechangeset.WeChatActivity;
import com.maijia.rc.databinding.ActivityExechangeSettingBinding;
import com.maijia.rc.di.component.DaggerUserComponent;
import com.maijia.rc.di.module.ActivityModule;
import com.maijia.rc.di.module.UserModule;

/**
 * 兑换设置
 */
public class ExechangeSettingActivity extends BaseActivity {
    private Context context;
    ActivityExechangeSettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_exechange_setting);
        ActivityStack.getInstance().pushActivity(this);
        setStatusBarColor();
        binding.titlebar.title.setText("兑换设置");
        initInject();
        initClick();
    }

    private void initClick() {
        binding.titlebar.back.setOnClickListener(this);
        //C1认证
        binding.llC1Identification.setOnClickListener(this);
        //C2认证
        binding.llC2Identification.setOnClickListener(this);
        //微信
        binding.llWechatAccount.setOnClickListener(this);
        //支付宝
        binding.llAlipayAccount.setOnClickListener(this);
        //银行卡
        binding.llBankCard.setOnClickListener(this);
        //其他支付方式
        binding.llOtherPay.setOnClickListener(this);
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
            case R.id.ll_C1_identification:
                C1IdentificationActivity.startAction(context);
                break;
            case R.id.ll_C2_identification:
                C2IdentificationActivity.startAction(context);
                break;
            case R.id.ll_wechat_account:
                WeChatActivity.startAction(context);
                break;
            case R.id.ll_alipay_account:
                ALiPayActivity.startAction(context);
                break;
            case R.id.ll_bank_card:
                BankCardActivity.startAction(context);
                break;
            case R.id.ll_other_pay:
                OtherPayStyleActivity.startAction(context);
                break;

        }
    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, ExechangeSettingActivity.class);
        context.startActivity(intent);
    }
}
