package com.maijia.rc.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.maijia.rc.R;
import com.maijia.rc.adapter.MainPagerAdapter;
import com.maijia.rc.databinding.FragmentStoreCenterBinding;

import java.util.ArrayList;


/**
 * 商城中心
 */
public class StoreCenterFragment extends BaseFragment {
    FragmentStoreCenterBinding binding;
    private MainPagerAdapter pagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_store_center, container, false);
        binding.titlebar.title.setText("商城");
        binding.rbMillStore.setTextColor(ContextCompat.getColor(getActivity(), R.color.blue_bg));
        binding.tvLeft.setTextColor(ContextCompat.getColor(getActivity(), R.color.blue_bg));
        binding.titlebar.back.setVisibility(View.INVISIBLE);
        initClick();
        setAdapter();

        return binding.getRoot();
    }

    private void setAdapter() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new MillStoreFragment());
        fragments.add(new ConsumeStoreFragment());
        pagerAdapter = new MainPagerAdapter(getChildFragmentManager(), fragments);
        binding.viewPager.setNoScroll(false);
        binding.viewPager.setAdapter(pagerAdapter);
        binding.viewPager.setOffscreenPageLimit(1);
    }

    private void initClick() {
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        binding.rbMillStore.setChecked(true);
                        break;
                    case 1:
                        binding.rbConsumeStore.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_mill_store:
                        binding.viewPager.setCurrentItem(0);
                        binding.rbMillStore.setTextColor(ContextCompat.getColor(getActivity(), R.color.blue_bg));
                        binding.rbConsumeStore.setTextColor(ContextCompat.getColor(getActivity(), R.color.home_text));
                        binding.tvLeft.setBackgroundColor(getResources().getColor(R.color.blue_bg));
                        binding.tvRight.setBackgroundColor(getResources().getColor(R.color.home_line));

                        break;
                    case R.id.rb_consume_store:
                        binding.viewPager.setCurrentItem(1);
                        binding.rbMillStore.setTextColor(ContextCompat.getColor(getActivity(), R.color.home_text));
                        binding.rbConsumeStore.setTextColor(ContextCompat.getColor(getActivity(), R.color.blue_bg));
                        binding.tvLeft.setBackgroundColor(getResources().getColor(R.color.home_line));
                        binding.tvRight.setBackgroundColor(getResources().getColor(R.color.blue_bg));

                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
//        switch () {
//        }
    }
}

