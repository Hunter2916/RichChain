package com.maijia.rc.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.maijia.domain.model.FindNickNameDetailData;
import com.maijia.rc.R;
import com.maijia.rc.adapter.FindNickNameDetailAdapter;
import com.maijia.rc.databinding.ActivityFindNickNameDetailBinding;

import java.util.ArrayList;
import java.util.List;

public class FindNickNameDetailActivity extends BaseActivity {
    private List<FindNickNameDetailData> dataList;
    ActivityFindNickNameDetailBinding binding;
    private FindNickNameDetailAdapter adapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().pushActivity(this);
        context = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_find_nick_name_detail);
        binding.titlebar.title.setText("用户名");

        setStatusBarColor();
        initClick();
        initView();
    }

    private void initView() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        dataList = new ArrayList<>();
        dataList.add(new FindNickNameDetailData("lyd22222"));
        dataList.add(new FindNickNameDetailData("lyd22288"));
        dataList.add(new FindNickNameDetailData("lyd88882"));
        dataList.add(new FindNickNameDetailData("lyd8822"));
        dataList.add(new FindNickNameDetailData("lyd55222"));
        dataList.add(new FindNickNameDetailData("lyd44522"));
        dataList.add(new FindNickNameDetailData("lyd99992"));
        dataList.add(new FindNickNameDetailData("lyd22666"));
        adapter = new FindNickNameDetailAdapter(dataList, context);
        binding.recyclerView.setAdapter(adapter);
    }

    private void initClick() {
        binding.titlebar.back.setOnClickListener(this);
        binding.toLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.to_login:
                LoginActivity.startAction(context);
                break;
        }
    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, FindNickNameDetailActivity.class);
        context.startActivity(intent);
    }
}
