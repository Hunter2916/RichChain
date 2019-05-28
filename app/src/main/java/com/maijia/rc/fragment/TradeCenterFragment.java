package com.maijia.rc.fragment;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.stetho.common.LogUtil;
import com.github.mikephil.charting.data.LineData;
import com.maijia.data.util.ToastUtil;
import com.maijia.domain.model.BuyRCData;
import com.maijia.rc.R;
import com.maijia.rc.adapter.BuyRCAdapter;
import com.maijia.rc.adapter.MainPagerAdapter;
import com.maijia.rc.databinding.FragmentTradeCenterBinding;
import com.maijia.rc.databinding.LayoutHeaderHomeBinding;
import com.maijia.rc.listener.OnBack1Listener;
import com.maijia.rc.utils.LineChartManagerUtil;
import com.maijia.rc.view.InnerScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * 交易
 */
public class TradeCenterFragment extends BaseFragment {
    FragmentTradeCenterBinding binding;
    private MainPagerAdapter pagerAdapter;
    private BuyRCAdapter adapter;
    private List<BuyRCData> list = new ArrayList<>();
    LayoutHeaderHomeBinding HeaderBinding;
    //默认可以滚动
    private boolean scroll = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_trade_center, container, false);
        binding.titlebar.title.setText("交易中心");
        binding.titlebar.back.setVisibility(View.GONE);
        LayoutInflater mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //加载头部布局
        HeaderBinding = DataBindingUtil.inflate(mInflater, R.layout.layout_header_home, binding.listView, false);
        LogUtil.e("首页加载...");
        //添加头部布局
        binding.listView.addHeaderView(HeaderBinding.headerHome);
        adapter = new BuyRCAdapter(getContext());
        binding.listView.setAdapter(adapter);
        binding.listView.setFocusable(false);
        initView();
        initChart();
        initClick();
        binding.scroll.setScrollListener(new InnerScrollView.ScrollListener() {
            @Override
            public void scrollOritention(int l, int t, int oldl, int oldt) {
                scroll = false;
            }
        });
        return binding.getRoot();
    }

    private void initChart() {
        //设置图表的描述
        HeaderBinding.lineChart.setDescription("");
        //设置x轴的数据
        int numX = 5;
        //设置Y轴的数据
        float[] datas = {0.001f, 0.002f, 0.003f, 0.004f, 0.005f};
        LineData lineData = LineChartManagerUtil.initSingleLineChart(getActivity(), HeaderBinding.lineChart, numX, datas);
        //是否开启折线图上的数字
        lineData.setDrawValues(false);
        LineChartManagerUtil.initDataStyle(HeaderBinding.lineChart, lineData, getActivity());
    }

    private void initView() {

//        ArrayList<Fragment> fragments = new ArrayList<>();
//        fragments.add(new BuyRCFragment());
//        fragments.add(new SaleRCFragment());
//        pagerAdapter = new MainPagerAdapter(getChildFragmentManager(), fragments);
//        binding.viewPager.setNoScroll(true);
//        binding.viewPager.setAdapter(pagerAdapter);
//        binding.viewPager.setOffscreenPageLimit(1);
        list.add(new BuyRCData(R.mipmap.logo_101, "李工", 9, 0.001, 44));
        list.add(new BuyRCData(R.mipmap.logo_101, "李工", 9, 0.001, 44));
        list.add(new BuyRCData(R.mipmap.logo_101, "哈工", 9, 0.001, 5));
        list.add(new BuyRCData(R.mipmap.logo_101, "王工", 9, 0.001, 8));
        list.add(new BuyRCData(R.mipmap.logo_101, "王工", 9, 0.001, 8));
        list.add(new BuyRCData(R.mipmap.logo_101, "牛工", 8, 0.006, 9));
        list.add(new BuyRCData(R.mipmap.logo_101, "李工", 9, 0.001, 8));
        list.add(new BuyRCData(R.mipmap.logo_101, "李工", 9, 0.001, 8));
        list.add(new BuyRCData(R.mipmap.logo_101, "李工", 9, 0.001, 8));
        list.add(new BuyRCData(R.mipmap.logo_101, "和工", 19, 0.001, 8));
        list.add(new BuyRCData(R.mipmap.logo_101, "和工", 19, 0.001, 8));
        list.add(new BuyRCData(R.mipmap.logo_101, "和工", 19, 0.001, 8));
        adapter.setData(list);
        adapter.notifyDataSetChanged();
    }

    private void initClick() {
        HeaderBinding.tvBuy.setOnClickListener(this);
        HeaderBinding.tvSale.setOnClickListener(this);
        adapter.setOnBack1Listener(new OnBack1Listener() {
            @Override
            public void bank1(int postion) {
                ToastUtil.show(getActivity(), "你点击的是：" + list.get(postion).getNickName());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_buy:
                HeaderBinding.tvBuy.setTextColor(ContextCompat.getColor(getActivity(), R.color.blue_bg));
                HeaderBinding.tvSale.setTextColor(ContextCompat.getColor(getActivity(), R.color.home_text));
                HeaderBinding.tvLeft.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.blue_bg));
                HeaderBinding.tvRight.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.home_line));
                HeaderBinding.llBuy.setVisibility(View.VISIBLE);
                HeaderBinding.llSale.setVisibility(View.GONE);
                list.clear();
                initView();
                break;
            case R.id.tv_sale:
                HeaderBinding.tvBuy.setTextColor(ContextCompat.getColor(getActivity(), R.color.home_text));
                HeaderBinding.tvSale.setTextColor(ContextCompat.getColor(getActivity(), R.color.blue_bg));
                HeaderBinding.tvLeft.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.home_line));
                HeaderBinding.tvRight.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.blue_bg));
                HeaderBinding.llSale.setVisibility(View.VISIBLE);
                HeaderBinding.llBuy.setVisibility(View.GONE);
                list.clear();
                initSaleData();
                adapter.setData(list);
                adapter.notifyDataSetChanged();
                break;
        }
    }

    private void initSaleData() {
        list.add(new BuyRCData(R.mipmap.logo_101, "哈哈", 9, 0.001, 44));
        list.add(new BuyRCData(R.mipmap.ic_launcher, "李工", 9, 0.001, 44));
        list.add(new BuyRCData(R.mipmap.logo_101, "哈工", 9, 0.001, 5));
        list.add(new BuyRCData(R.mipmap.ic_launcher, "王工", 9, 0.001, 8));
        list.add(new BuyRCData(R.mipmap.logo_101, "王工", 9, 0.001, 8));
        list.add(new BuyRCData(R.mipmap.logo_101, "牛工", 8, 0.006, 9));
        list.add(new BuyRCData(R.mipmap.ic_launcher, "李工", 9, 0.001, 8));
        list.add(new BuyRCData(R.mipmap.logo_101, "李工", 9, 0.001, 8));
        list.add(new BuyRCData(R.mipmap.ic_launcher, "李工", 9, 0.001, 8));
        list.add(new BuyRCData(R.mipmap.logo_101, "和工", 19, 0.001, 8));
        list.add(new BuyRCData(R.mipmap.ic_launcher, "和工", 19, 0.001, 8));
        list.add(new BuyRCData(R.mipmap.logo_101, "和工", 19, 0.001, 8));
    }
}
