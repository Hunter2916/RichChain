package com.maijia.rc.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.maijia.rc.R;
import com.maijia.rc.databinding.ActivityRccanTradeBinding;

public class RCCanTradeActivity extends BaseActivity {
    ActivityRccanTradeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rccan_trade);
        ActivityStack.getInstance().pushActivity(this);
        binding.titlebar.title.setText("可交易富链详情");
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
        Intent intent = new Intent(context, RCCanTradeActivity.class);
        context.startActivity(intent);
    }
}
