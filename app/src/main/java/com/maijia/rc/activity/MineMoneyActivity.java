package com.maijia.rc.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.maijia.rc.R;
import com.maijia.rc.databinding.ActivityMineMoneyBinding;
import com.maijia.rc.di.component.DaggerUserComponent;
import com.maijia.rc.di.module.ActivityModule;
import com.maijia.rc.di.module.UserModule;
import com.maijia.rc.dialog.DialogManager;
import com.maijia.rc.listener.OnDialogClickListener;
import com.maijia.rc.utils.DateUtil;

public class MineMoneyActivity extends BaseActivity {
    private Context context;
    ActivityMineMoneyBinding binding;
    private int addTime;
    private DialogManager dialogManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mine_money);
        binding.titlebar.title.setText("我的资产");
        ActivityStack.getInstance().pushActivity(this);
        dialogManager = new DialogManager();
        setStatusBarColor();
        initInject();
        initClick();
        binding.time.setText("今天是" + DateUtil.getDateTimeYeartoChinese(System.currentTimeMillis()));
        setMineMoneyTextColor();
    }

    private void setMineMoneyTextColor() {
        addTime = 138;
        String str = "你已加入区块链<font color='#AE26FC'>" + addTime + "</font>天";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            binding.addTime.setText(Html.fromHtml(str, Html.FROM_HTML_MODE_LEGACY));
        } else {
            binding.addTime.setText(Html.fromHtml(str));
        }
    }

    private void initClick() {
        binding.titlebar.back.setOnClickListener(this);
        binding.ivRcCan.setOnClickListener(this);
        binding.tvRcCanTrade.setOnClickListener(this);
        binding.tvRcCanDetail.setOnClickListener(this);

        binding.ivRcNot.setOnClickListener(this);
        binding.tvRcNotTrade.setOnClickListener(this);
        binding.tvRcNotDetail.setOnClickListener(this);

        binding.ivKs.setOnClickListener(this);
        binding.tvKsTrade.setOnClickListener(this);
        binding.tvKsDetail.setOnClickListener(this);

        binding.ivKj.setOnClickListener(this);
        binding.tvKjTrade.setOnClickListener(this);
        binding.tvKjDetail.setOnClickListener(this);

        binding.ivKjp.setOnClickListener(this);
        binding.tvKjpTrade.setOnClickListener(this);
        binding.tvKjpDetail.setOnClickListener(this);

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
            //富链可交易
            case R.id.iv_rc_can:

                dialogManager.setDialogTitleAndLongMessage(context, "这是一台哦很长很长的信息", "我知道了", "可交易富链");
                dialogManager.setOnSaveClickListener(new OnDialogClickListener() {
                    @Override
                    public void onSaveClick() {

                    }

                    @Override
                    public void onCancelClick() {

                    }
                });

                break;
            case R.id.tv_rc_can_trade:
                break;
            case R.id.tv_rc_can_detail:
                RCCanTradeActivity.startAction(context);
                break;
            //富链不可交易
            case R.id.iv_rc_not:

                dialogManager.setDialogTitleAndLongMessage(context, "这是一台哦很长很长的信息", "我知道了", "不可交易富链");
                dialogManager.setOnSaveClickListener(new OnDialogClickListener() {
                    @Override
                    public void onSaveClick() {

                    }

                    @Override
                    public void onCancelClick() {

                    }
                });
                break;
            case R.id.tv_rc_not_trade:
                break;
            case R.id.tv_rc_not_detail:
                RCNotTradeActivity.startAction(context);
                break;
            //矿石
            case R.id.iv_ks:

                dialogManager.setDialogTitleAndLongMessage(context, "这是一台哦很长很长的信息", "我知道了", "矿石");
                dialogManager.setOnSaveClickListener(new OnDialogClickListener() {
                    @Override
                    public void onSaveClick() {

                    }

                    @Override
                    public void onCancelClick() {

                    }
                });
                break;
            case R.id.tv_ks_trade:
                break;
            case R.id.tv_ks_detail:
                OreActivity.startAction(context);
                break;
            //矿机
            case R.id.iv_kj:

                dialogManager.setDialogTitleAndLongMessage(context, "这是一台哦很长很长的信息", "我知道了", "矿机");
                dialogManager.setOnSaveClickListener(new OnDialogClickListener() {
                    @Override
                    public void onSaveClick() {

                    }

                    @Override
                    public void onCancelClick() {

                    }
                });
                break;
            case R.id.tv_kj_trade:
                break;
            case R.id.tv_kj_detail:
                MillActivity.startAction(context);
                break;
            //矿机票
            case R.id.iv_kjp:

                dialogManager.setDialogTitleAndLongMessage(context, "这是一台哦很长很长的信息", "我知道了", "可交易矿机票");
                dialogManager.setOnSaveClickListener(new OnDialogClickListener() {
                    @Override
                    public void onSaveClick() {

                    }

                    @Override
                    public void onCancelClick() {

                    }
                });
                break;
            case R.id.tv_kjp_trade:
                break;
            case R.id.tv_kjp_detail:
                MillTicketActivity.startAction(context);
                break;

        }

    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, MineMoneyActivity.class);
        context.startActivity(intent);

    }
}
