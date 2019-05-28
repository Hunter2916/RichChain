package com.maijia.rc.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maijia.data.util.ToastUtil;
import com.maijia.domain.model.HomePageData;
import com.maijia.rc.R;
import com.maijia.rc.activity.HomePageDetailActivity;
import com.maijia.rc.adapter.HomePageAdapter;
import com.maijia.rc.databinding.FragmentHomePageBinding;
import com.maijia.rc.databinding.LayoutHeaderHomePageBinding;
import com.maijia.rc.listener.OnBack1Listener;
import com.maijia.rc.loader.GlideImageLoader;
import com.paradoxie.autoscrolltextview.VerticalTextview;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 */
public class HomePageFragment extends BaseFragment {
    private HomePageAdapter adapter;
    private List<HomePageData> homePageDataList;
    FragmentHomePageBinding binding;
    LayoutHeaderHomePageBinding HeaderBinding;
    private ArrayList<String> titleList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false);
        HeaderBinding = DataBindingUtil.inflate(inflater, R.layout.layout_header_home_page, container, false);

        binding.titlebar.back.setVisibility(View.GONE);
        binding.titlebar.title.setText("首页");
        initView();
        initAutoText();
        initClick();
        adapter.setHeader(HeaderBinding.getRoot());
        return binding.getRoot();
    }

    private void initAutoText() {
        titleList.add("你是天上最受宠的一架钢琴");
        titleList.add("我是丑人脸上的鼻涕");
        titleList.add("你发出完美的声音");
        titleList.add("我被默默揩去");
        HeaderBinding.info.tvNotice.setTextList(titleList);
        HeaderBinding.info.tvNotice.setTextStillTime(5000);//设置停留时长间隔
        HeaderBinding.info.tvNotice.setAnimTime(600);//设置进入和退出的时间间隔
//        binding.nestedScrollview.fling(-10000);
    }

    private void initView() {
        //本地图片数据（资源文件）
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.b1);
        list.add(R.mipmap.b2);
        list.add(R.mipmap.b3);
        HeaderBinding.banner.setImages(list)
                .setImageLoader(new GlideImageLoader())
                .isAutoPlay(true)
                .setDelayTime(5000)
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        ToastUtil.show(getActivity(),"你点击的是：："+position);
                    }
                })
                .start();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        binding.recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        homePageDataList = new ArrayList<>();
        homePageDataList.add(new HomePageData(R.mipmap.ic_zx_1, "行业内部资讯", "", "2018-06-05 12:30:00", "管理员"));
        homePageDataList.add(new HomePageData(R.mipmap.ic_zx_2, "行业内部资讯", "", "2018-06-09 12:30:00", "管理员"));
        homePageDataList.add(new HomePageData(R.mipmap.ic_zx_3, "行业内部资讯", "", "2018-06-10 12:30:00", "管理员"));
        homePageDataList.add(new HomePageData(R.mipmap.ic_zx_3, "行业内部资讯", "", "2018-06-11 12:30:00", "管理员"));
        homePageDataList.add(new HomePageData(R.mipmap.ic_zx_1, "行业内部资讯", "", "2018-06-15 12:30:00", "管理员"));
        homePageDataList.add(new HomePageData(R.mipmap.ic_zx_3, "行业内部资讯", "", "2018-06-17 12:30:00", "管理员"));
        homePageDataList.add(new HomePageData(R.mipmap.ic_zx_2, "行业内部资讯", "", "2018-06-18 12:30:00", "管理员"));
        adapter = new HomePageAdapter(homePageDataList, getActivity());
        binding.recyclerView.setAdapter(adapter);
    }

    private void initClick() {
        HeaderBinding.info.tvNotice.setOnItemClickListener(new VerticalTextview.OnItemClickListener() {
            @Override
            public void onItemClick(int i) {
                ToastUtil.show(getActivity(), "你点击的是：" + titleList.get(i));
            }
        });
        adapter.setOnBack1Listener(new OnBack1Listener() {
            @Override
            public void bank1(int postion) {
                HomePageDetailActivity.startAction(getActivity(), homePageDataList.get(postion));
            }
        });

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onResume() {
        HeaderBinding.info.tvNotice.startAutoScroll();
        HeaderBinding.banner.startAutoPlay();
        super.onResume();
    }

    @Override
    public void onPause() {
        HeaderBinding.info.tvNotice.stopAutoScroll();
        HeaderBinding.banner.stopAutoPlay();
        super.onPause();
    }
}
