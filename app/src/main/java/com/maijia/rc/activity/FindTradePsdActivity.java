package com.maijia.rc.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.maijia.rc.R;
import com.maijia.rc.databinding.ActivityFindTradePsdBinding;

public class FindTradePsdActivity extends BaseActivity {
    ActivityFindTradePsdBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().pushActivity(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_find_trade_psd);
        binding.login.setText("确认");
        setStatusBarColor();
        binding.titlebar.title.setText("找交易回密码");
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
        Intent intent = new Intent(context, FindTradePsdActivity.class);
        context.startActivity(intent);
    }
}
