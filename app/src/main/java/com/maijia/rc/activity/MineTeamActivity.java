package com.maijia.rc.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.maijia.rc.R;
import com.maijia.rc.adapter.MainPagerAdapter;
import com.maijia.rc.databinding.ActivityMineTeamBinding;
import com.maijia.rc.fragment.PromoteFragment;
import com.maijia.rc.fragment.TeamFragment;

import java.util.ArrayList;

public class MineTeamActivity extends BaseActivity {
    ActivityMineTeamBinding binding;
    private MainPagerAdapter pagerAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mine_team);
        ActivityStack.getInstance().pushActivity(this);
        binding.titlebar.title.setText("团队推广");
        setStatusBarColor();
        initClick();

        //初始化底部数据
        setAdapter();
        binding.teamBtBg.setBackgroundResource(R.color.blue_bg);
    }

    private void setAdapter() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new TeamFragment());
        fragments.add(new PromoteFragment());
        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);
        binding.viewPager.setAdapter(pagerAdapter);
        binding.viewPager.setNoScroll(true);
        binding.viewPager.setOffscreenPageLimit(1);

    }

    private void initClick() {
        binding.titlebar.back.setOnClickListener(this);
        binding.llTeam.setOnClickListener(this);
        binding.llPromote.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.ll_team:
                binding.viewPager.setCurrentItem(0);
                binding.tvTeam.setTextColor(ContextCompat.getColor(this, R.color.blue_bg));
                binding.tvPromote.setTextColor(ContextCompat.getColor(this, R.color.black_light));
                binding.teamBtBg.setBackgroundColor(ContextCompat.getColor(this, R.color.blue_bg));
                binding.promoteBtBg.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
                break;
            case R.id.ll_promote:
                binding.viewPager.setCurrentItem(1);
                binding.tvTeam.setTextColor(ContextCompat.getColor(this, R.color.black_light));
                binding.tvPromote.setTextColor(ContextCompat.getColor(this, R.color.blue_bg));
                binding.teamBtBg.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
                binding.promoteBtBg.setBackgroundColor(ContextCompat.getColor(this, R.color.blue_bg));
                break;
        }
    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, MineTeamActivity.class);
        context.startActivity(intent);
    }
}
