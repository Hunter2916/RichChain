package com.maijia.rc.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.maijia.data.util.ToastUtil;
import com.maijia.domain.model.HomePageData;
import com.maijia.rc.R;
import com.maijia.rc.databinding.ActivityHomePageDetailBinding;

public class HomePageDetailActivity extends BaseActivity {
    private Context context;
    ActivityHomePageDetailBinding binding;
    private HomePageData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_page_detail);
        ActivityStack.getInstance().pushActivity(this);
        setStatusBarColor();
        data = (HomePageData) getIntent().getSerializableExtra("data");
        binding.titlebar.title.setText("资讯详情");
        initClick();
        initView();
    }

    private void initView() {
        ToastUtil.show(context, "你点击的是:" + data.getTitle());
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

    public static void startAction(Context context, HomePageData data) {
        Intent intent = new Intent(context, HomePageDetailActivity.class);
        intent.putExtra("data", data);
        context.startActivity(intent);
    }
}
