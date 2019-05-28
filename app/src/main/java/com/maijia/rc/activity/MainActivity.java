package com.maijia.rc.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import com.maijia.rc.R;
import com.maijia.rc.adapter.MainPagerAdapter;
import com.maijia.rc.databinding.ActivityMainBinding;
import com.maijia.rc.di.component.DaggerUserComponent;
import com.maijia.rc.di.module.ActivityModule;
import com.maijia.rc.di.module.UserModule;
import com.maijia.rc.fragment.HomePageFragment;
import com.maijia.rc.fragment.MineCenterFragment;
import com.maijia.rc.fragment.StoreCenterFragment;
import com.maijia.rc.fragment.TradeCenterFragment;

import java.util.ArrayList;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity {
    ActivityMainBinding binding;
    private MainPagerAdapter pagerAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ActivityStack.getInstance().pushActivity(this);
        setStatusBarColor();
        initInject();
        initClick();
        //初始化底部数据
        setButtonAdapter();
        binding.rbHomePage.setTextColor(ContextCompat.getColor(this, R.color.white));
    }

    private void setButtonAdapter() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        //首页
        fragments.add(new HomePageFragment());
        //商城
        fragments.add(new StoreCenterFragment());
        //交易
        fragments.add(new TradeCenterFragment());
        //我的
        fragments.add(new MineCenterFragment());
        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);
        binding.viewPager.setAdapter(pagerAdapter);
        binding.viewPager.setNoScroll(true);
        binding.viewPager.setOffscreenPageLimit(3);
    }

    private void initInject() {
        DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .userModule(new UserModule())
                .build()
                .inject(this);
    }

    /**
     * viewpager的监听的方法进行了改进改为addOnPageChangeListener()
     */
    private void initClick() {
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    //首页
                    case 0:
                        binding.rbHomePage.setChecked(true);
                        break;
                    //商城
                    case 1:
                        binding.rbStoreCenter.setChecked(true);
                        break;
                    //交易
                    case 2:
                        binding.rbTradeCenter.setChecked(true);
                        break;
                    //会员
                    case 3:
                        binding.rbMemberCenter.setChecked(true);
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
                    //首页
                    case R.id.rb_home_page:
                        binding.viewPager.setCurrentItem(0);
                        binding.rbHomePage.setTextColor(ContextCompat.getColor(context, R.color.white));
                        binding.rbStoreCenter.setTextColor(ContextCompat.getColor(context, R.color.home_text));
                        binding.rbTradeCenter.setTextColor(ContextCompat.getColor(context, R.color.home_text));
                        binding.rbMemberCenter.setTextColor(ContextCompat.getColor(context, R.color.home_text));
                        break;
                    //商城
                    case R.id.rb_store_center:
                        binding.viewPager.setCurrentItem(1);
                        binding.rbHomePage.setTextColor(ContextCompat.getColor(context, R.color.home_text));
                        binding.rbStoreCenter.setTextColor(ContextCompat.getColor(context, R.color.white));
                        binding.rbTradeCenter.setTextColor(ContextCompat.getColor(context, R.color.home_text));
                        binding.rbMemberCenter.setTextColor(ContextCompat.getColor(context, R.color.home_text));
                    break;
                    //交易
                    case R.id.rb_trade_center:
                        binding.viewPager.setCurrentItem(2);
                        binding.rbHomePage.setTextColor(ContextCompat.getColor(context, R.color.home_text));
                        binding.rbStoreCenter.setTextColor(ContextCompat.getColor(context, R.color.home_text));
                        binding.rbTradeCenter.setTextColor(ContextCompat.getColor(context, R.color.white));
                        binding.rbMemberCenter.setTextColor(ContextCompat.getColor(context, R.color.home_text));
                        break;
                    //我的
                    case R.id.rb_member_center:
                        binding.viewPager.setCurrentItem(3);
                        binding.rbHomePage.setTextColor(ContextCompat.getColor(context, R.color.home_text));
                        binding.rbStoreCenter.setTextColor(ContextCompat.getColor(context, R.color.home_text));
                        binding.rbTradeCenter.setTextColor(ContextCompat.getColor(context, R.color.home_text));
                        binding.rbMemberCenter.setTextColor(ContextCompat.getColor(context, R.color.white));
                        break;
                }
            }
        });
    }


    @Override
    public void onClick(View view) {

    }
}
