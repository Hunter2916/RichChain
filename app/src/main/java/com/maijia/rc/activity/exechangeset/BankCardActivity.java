package com.maijia.rc.activity.exechangeset;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.maijia.rc.R;
import com.maijia.rc.activity.ActivityStack;
import com.maijia.rc.activity.BaseActivity;
import com.maijia.rc.databinding.ActivityBankCardBinding;

public class BankCardActivity extends BaseActivity {
    ActivityBankCardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bank_card);
        ActivityStack.getInstance().pushActivity(this);
        binding.titlebar.title.setText("添加银行卡");
        setStatusBarColor();
        initClick();
    }

    private void initClick() {
        binding.titlebar.back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, BankCardActivity.class);
        context.startActivity(intent);
    }
}
