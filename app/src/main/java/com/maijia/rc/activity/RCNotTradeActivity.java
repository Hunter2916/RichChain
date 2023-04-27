package com.maijia.rc.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.maijia.rc.R;
import com.maijia.rc.databinding.ActivityRcnotTradeBinding;

public class RCNotTradeActivity extends BaseActivity {
    ActivityRcnotTradeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rcnot_trade);
        ActivityStack.getInstance().pushActivity(this);
        setStatusBarColor();
        binding.titlebar.title.setText("不可交易富链详情");
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
        Intent intent = new Intent(context, RCNotTradeActivity.class);
        context.startActivity(intent);
    }
}
