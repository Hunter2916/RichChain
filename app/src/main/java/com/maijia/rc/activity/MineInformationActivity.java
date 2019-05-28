package com.maijia.rc.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.maijia.data.util.ToastUtil;
import com.maijia.rc.R;
import com.maijia.rc.databinding.ActivityMineInformationBinding;
import com.maijia.rc.di.component.DaggerUserComponent;
import com.maijia.rc.di.module.ActivityModule;
import com.maijia.rc.di.module.UserModule;

/**
 * 个人资料
 */
public class MineInformationActivity extends BaseActivity {
    private Context context;
    ActivityMineInformationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mine_information);

        ActivityStack.getInstance().pushActivity(this);
        setStatusBarColor();
        binding.titlebar.title.setText("个人资料");
        initInject();
        initClick();
        initView();

    }

    private void initView() {
        binding.spinner.setItems( "男", "女", "其他");
        binding.spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                ToastUtil.show(context, "你点击的是：" + item.toString());
            }
        });

    }

    private void initClick() {
        binding.titlebar.back.setOnClickListener(this);
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
        }
    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, MineInformationActivity.class);
        context.startActivity(intent);
    }
}
