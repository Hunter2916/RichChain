package com.maijia.rc.fragment;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maijia.domain.model.MineCenterIconData;
import com.maijia.rc.R;
import com.maijia.rc.activity.MineMoneyActivity;
import com.maijia.rc.activity.MineTeamActivity;
import com.maijia.rc.activity.SettingActivity;
import com.maijia.rc.adapter.MineCenterIconAdapter;
import com.maijia.rc.databinding.FragmentMemberCenterBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 会员中心
 */
public class MineCenterFragment extends BaseFragment {
    FragmentMemberCenterBinding binding;
    private List<MineCenterIconData> dataList;
    private MineCenterIconAdapter adapter;
    private int leftRight;
    private int topBottom;
    private int spanCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_center, container, false);
        binding.titlebar.plugin.setImageResource(R.mipmap.ic_homepage_news);
        binding.titlebar.title.setText("会员中心");
        binding.titlebar.back.setVisibility(View.INVISIBLE);
        initClick();
//        initView();
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    /**
     * 初始化数据
     */
    private void initView() {
        //设置网格布局：
//        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL,false));
        dataList = new ArrayList<>();
        dataList.add(new MineCenterIconData(R.mipmap.ic_mine_property, "我的资产"));
        dataList.add(new MineCenterIconData(R.mipmap.ic_mine_team, "团队/推广"));
        dataList.add(new MineCenterIconData(R.mipmap.ic_mine_order, "订单管理"));
        dataList.add(new MineCenterIconData(R.mipmap.ic_mine_exchange, "兑换中心"));
        dataList.add(new MineCenterIconData(R.mipmap.ic_mine_serve, "我的服务"));
        dataList.add(new MineCenterIconData(R.mipmap.ic_mine_course, "新手教程"));
        dataList.add(new MineCenterIconData(R.mipmap.ic_mine_setting, "设置"));
//        adapter = new MineCenterIconAdapter(dataList, getActivity(), this, 3, 15, 15);
        adapter = new MineCenterIconAdapter(dataList, getActivity());

//        binding.recyclerView.addItemDecoration(new MyDividerItem(15,15));
//        binding.recyclerView.setAdapter(adapter);
    }

    private void initClick() {
        binding.titlebar.back.setOnClickListener(this);
        binding.llMineMoney.setOnClickListener(this);
        binding.llMineTeam.setOnClickListener(this);
        binding.llMineOrder.setOnClickListener(this);
        binding.llMineExchange.setOnClickListener(this);
        binding.llMineServe.setOnClickListener(this);
        binding.llMineTeach.setOnClickListener(this);
        binding.llMineSetting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_mine_money:
                MineMoneyActivity.startAction(getActivity());
                break;
            case R.id.ll_mine_team:
                MineTeamActivity.startAction(getActivity());
                break;
            case R.id.ll_mine_order:
                break;
            case R.id.ll_mine_exchange:
                break;
            case R.id.ll_mine_serve:
                break;
            case R.id.ll_mine_teach:
                break;
            case R.id.ll_mine_setting:
                SettingActivity.startAction(getActivity());
                break;
        }
    }
}
