package com.maijia.rc.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maijia.rc.R;
import com.maijia.rc.adapter.MainPagerAdapter;
import com.maijia.rc.databinding.FragmentMineMillBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的矿机中心
 */
public class MineMillFragment extends BaseFragment {
    FragmentMineMillBinding binding;
    private List<Fragment> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine_mill, container, false);
        binding.titlebar.back.setVisibility(View.INVISIBLE);
        binding.titlebar.title.setText("我的矿机");
        binding.viewPager.setNoScroll(true);
        initClick();
        initView();
        return binding.getRoot();
    }

    private void initView() {
        list.add(new EffectiveMillFragment());
        list.add(new DiscardMillFragment());
        binding.viewPager.setAdapter(new MainPagerAdapter(getChildFragmentManager(), list));
    }

    private void initClick() {
        binding.llEffective.setOnClickListener(this);
        binding.llDiscard.setOnClickListener(this);
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_effective:
                binding.viewPager.setCurrentItem(0);
                binding.tvEffective.setBackgroundResource(R.color.purple);
                binding.tvDiscard.setBackgroundResource(R.color.black_light);
                break;
            case R.id.ll_discard:
                binding.viewPager.setCurrentItem(1);
                binding.tvEffective.setBackgroundResource(R.color.black_light);
                binding.tvDiscard.setBackgroundResource(R.color.purple);
                break;
        }
    }
}
