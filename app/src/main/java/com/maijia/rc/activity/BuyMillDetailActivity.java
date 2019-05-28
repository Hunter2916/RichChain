package com.maijia.rc.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.maijia.domain.model.MillStoreData;
import com.maijia.rc.R;
import com.maijia.rc.databinding.ActivityBuyMillDetailBinding;
import com.maijia.rc.di.component.DaggerUserComponent;
import com.maijia.rc.di.module.ActivityModule;
import com.maijia.rc.di.module.UserModule;

public class BuyMillDetailActivity extends BaseActivity {
    private Context context;
    ActivityBuyMillDetailBinding binding;
    private MillStoreData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().pushActivity(this);
        context = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_buy_mill_detail);
        setStatusBarColor();
        binding.titlebar.title.setText("矿机详情");
        Intent intent = getIntent();
        data = (MillStoreData) intent.getSerializableExtra("data");
        initClick();
        initInject();
        initView();
    }

    private void initView() {
        if (data != null) {
            binding.name.setText(data.getMillName());
        }
    }

    private void initInject() {
        DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .userModule(new UserModule())
                .build()
                .inject(this);
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

    public static void startAction(Context context, MillStoreData data) {
        Intent intent = new Intent(context, BuyMillDetailActivity.class);
        intent.putExtra("data", data);
        context.startActivity(intent);
    }
}
