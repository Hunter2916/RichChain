package com.maijia.rc.activity.exechangeset;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.maijia.rc.R;
import com.maijia.rc.activity.ActivityStack;
import com.maijia.rc.activity.BaseActivity;
import com.maijia.rc.databinding.ActivityAliPayBinding;

public class ALiPayActivity extends BaseActivity {
    ActivityAliPayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ali_pay);
        ActivityStack.getInstance().pushActivity(this);
        binding.titlebar.title.setText("添加支付宝");
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
        Intent intent = new Intent(context, ALiPayActivity.class);
        context.startActivity(intent);
    }
}
