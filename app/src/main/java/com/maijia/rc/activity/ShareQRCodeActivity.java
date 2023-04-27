package com.maijia.rc.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.maijia.data.util.ToastUtil;
import com.maijia.rc.R;
import com.maijia.rc.databinding.ActivityShareQrcodeBinding;
import com.maijia.rc.utils.QRCodeUtil;


public class ShareQRCodeActivity extends BaseActivity {
    private Context context;
    ActivityShareQrcodeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_share_qrcode);
        ActivityStack.getInstance().pushActivity(this);
        setStatusBarColor();
        binding.titlebar.title.setText("分享");
        initClick();
        initQRCode();
    }

    /**
     * 初始化二维码
     */
    private void initQRCode() {
        String codeURl = "https://www.pgyer.com/8n9z";
        Bitmap codeBitmap = QRCodeUtil.createBitmap(codeURl);
        binding.shareCode.setImageBitmap(QRCodeUtil.addLogo(codeBitmap, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_rc_logo)));
    }

    private void initClick() {
        binding.titlebar.back.setOnClickListener(this);
        binding.copy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.copy:
                ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setPrimaryClip(ClipData.newPlainText("text", "https://www.lanhuapp.com/web/#/item/board/detail?pid=42770b34-4cc9-4846-b3c5-a134f0f60dcaproject_id=42770b34-4cc9-4846-b3c5-a134f0f60dca"));
                if (cm.hasPrimaryClip()) {
                    cm.getPrimaryClip().getItemAt(0).getText();
                }
                ToastUtil.show(context, "复制成功");
                break;
        }
    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, ShareQRCodeActivity.class);
        context.startActivity(intent);
    }
}
