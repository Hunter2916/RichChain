package com.maijia.rc.activity.exechangeset;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.maijia.rc.R;
import com.maijia.rc.activity.ActivityStack;
import com.maijia.rc.activity.BaseActivity;
import com.maijia.rc.databinding.ActivityC1IdentificationBinding;

public class C1IdentificationActivity extends BaseActivity {
    ActivityC1IdentificationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_c1_identification);
        ActivityStack.getInstance().pushActivity(this);
        binding.titlebar.title.setText("C1认证");
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
        Intent intent = new Intent(context, C1IdentificationActivity.class);
        context.startActivity(intent);
    }
}
