package com.maijia.rc.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.maijia.rc.R;
import com.maijia.rc.databinding.ActivityAlterTradePasswordBinding;

public class AlterTradePasswordActivity extends BaseActivity {
    ActivityAlterTradePasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_alter_trade_password);
        ActivityStack.getInstance().pushActivity(this);
        setStatusBarColor();
        binding.titlebar.title.setText("修改交易密码");
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
        Intent intent = new Intent(context, AlterTradePasswordActivity.class);
        context.startActivity(intent);
    }
}
