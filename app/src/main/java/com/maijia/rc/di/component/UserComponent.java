package com.maijia.rc.di.component;


import com.maijia.rc.activity.AlterPasswordActivity;
import com.maijia.rc.activity.AlterTradePasswordActivity;
import com.maijia.rc.activity.BuyMillDetailActivity;
import com.maijia.rc.activity.ExechangeSettingActivity;
import com.maijia.rc.activity.FindNickNameActivity;
import com.maijia.rc.activity.FindNickNameDetailActivity;
import com.maijia.rc.activity.FindTradePsdActivity;
import com.maijia.rc.activity.ForgetPadActivity;
import com.maijia.rc.activity.HomePageDetailActivity;
import com.maijia.rc.activity.LoginActivity;
import com.maijia.rc.activity.MainActivity;
import com.maijia.rc.activity.MillActivity;
import com.maijia.rc.activity.MillTicketActivity;
import com.maijia.rc.activity.MineInformationActivity;
import com.maijia.rc.activity.MineMoneyActivity;
import com.maijia.rc.activity.MineTeamActivity;
import com.maijia.rc.activity.OreActivity;
import com.maijia.rc.activity.RCCanTradeActivity;
import com.maijia.rc.activity.RCNotTradeActivity;
import com.maijia.rc.activity.RegisterActivity;
import com.maijia.rc.activity.SettingActivity;
import com.maijia.rc.activity.ShareQRCodeActivity;
import com.maijia.rc.activity.exechangeset.ALiPayActivity;
import com.maijia.rc.activity.exechangeset.BankCardActivity;
import com.maijia.rc.activity.exechangeset.C1IdentificationActivity;
import com.maijia.rc.activity.exechangeset.C2IdentificationActivity;
import com.maijia.rc.activity.exechangeset.OtherPayStyleActivity;
import com.maijia.rc.activity.exechangeset.WeChatActivity;
import com.maijia.rc.di.PerActivity;
import com.maijia.rc.di.module.ActivityModule;
import com.maijia.rc.di.module.UserModule;

import dagger.Component;

/**
 * Created by XiaoKong on 2017/6/15.
 * Desription
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserModule.class})
public interface UserComponent extends ActivityComponent {
    /**
     * inject()，其中的参数就是我们需要在哪个类中实例化LoginPreserentCompl，因为我们需要在LoginActivity中
     * 实例化，所以参数类型就是LoginActivity类型。
     *
     * @param activity
     */
    void inject(MainActivity activity);

    void inject(LoginActivity activity);

    void inject(ForgetPadActivity activity);

    void inject(RegisterActivity activity);

    void inject(BuyMillDetailActivity activity);

    void inject(FindNickNameActivity activity);

    void inject(FindNickNameDetailActivity activity);

    void inject(MineMoneyActivity activity);

    void inject(RCCanTradeActivity activity);

    void inject(RCNotTradeActivity activity);

    void inject(OreActivity activity);

    void inject(MillActivity activity);

    void inject(MillTicketActivity activity);

    void inject(SettingActivity activity);

    void inject(MineInformationActivity activity);

    void inject(AlterPasswordActivity activity);

    void inject(AlterTradePasswordActivity activity);

    //找回交易密码
    void inject(FindTradePsdActivity activity);

    //兑换设置
    void inject(ExechangeSettingActivity activity);

    //其他支付方式
    void inject(OtherPayStyleActivity activity);

    //C1认证
    void inject(C1IdentificationActivity activity);

    void inject(C2IdentificationActivity activity);

    void inject(WeChatActivity activity);

    void inject(ALiPayActivity activity);

    void inject(BankCardActivity activity);

    void inject(MineTeamActivity activity);

    void inject(ShareQRCodeActivity activity);

    void inject(HomePageDetailActivity activity);
}
