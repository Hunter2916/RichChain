package com.maijia.rc.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.maijia.data.util.ScreenUtils;
import com.maijia.rc.R;
import com.maijia.rc.adapter.ListAdapter;
import com.maijia.rc.listener.OnDialogClickListener;
import com.maijia.rc.listener.OnSaveCancelListener;
import com.maijia.rc.listener.OnSaveListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/***
 * 对话框
 */
public class DialogManager {
    private Dialog mDialog;

    private OnDialogClickListener mListener;
    private OnSaveListener mSaveListener;
    private OnSaveCancelListener mSaveCancelListener;

    public void setOnSaveClickListener(OnDialogClickListener listener) {
        mListener = listener;
    }

    public void setOnSaveListener(OnSaveListener listener) {
        mSaveListener = listener;
    }

    public void setOnSaveCancelListener(OnSaveCancelListener mSaveCancelListener) {
        this.mSaveCancelListener = mSaveCancelListener;
    }

    /**
     * 对话框（包括 内容、确定和取消）
     *
     * @param mContext
     * @return
     */
    public void mySettingBuilder(Context mContext, String messageStr) {
        setDialogHint(mContext, messageStr, null, null);
    }

    /**
     * 对话框（包括 内容、确定和取消）
     *
     * @param mContext
     * @return
     */
    public void mySettingBuilder(Context mContext, String messageStr, String confirmStr) {
        setDialogHint(mContext, messageStr, confirmStr, null);
    }

    /**
     * 通用对话框（包括 内容、确定和 标题）
     *
     * @param mContext
     * @param resid
     * @param confirmStr
     */
    public void setDialogPictureAndMessage(Context mContext, String messageTopStr, String messageMiddleStr, String confirmStr, int resid) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View CustomView;
        CustomView = inflater.inflate(R.layout.custom_dialog_withdraw, null);

        ImageView image = (ImageView) CustomView.findViewById(R.id.tv_custom_dialog_setting_image);

        TextView messagetopStr = (TextView) CustomView.findViewById(R.id.tv_custom_dialog_setting_messageTopStr);
        TextView messagemiddleStr = (TextView) CustomView.findViewById(R.id.tv_custom_dialog_setting_messageMiddleStr);
        TextView confirm = (TextView) CustomView.findViewById(R.id.tv_custom_dialog_setting_confirm);
        if (!String.valueOf(resid).equals("null")) {
//            Picasso.with(mContext).load(resid).placeholder(R.mipmap.z_1).into(image);
            Picasso.with(mContext).load(resid).into(image);
        }
        if (null != messageTopStr && !messageTopStr.equals("")) {
            messagetopStr.setText(messageTopStr);
        } else {
            messagetopStr.setVisibility(View.GONE);
        }
        if (null != messageMiddleStr && !messageMiddleStr.equals("")) {
            messagemiddleStr.setText(messageMiddleStr);
        } else {
            messagemiddleStr.setVisibility(View.GONE);
        }
        if (null != confirm) {
            confirm.setText(confirmStr);
        }


        if (confirm != null) {
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        mListener.onSaveClick();
                    }
                    dimissDialog();
                }
            });
        }

        mDialog = new Dialog(mContext, R.style.Theme_AudioDialog);
        mDialog.setContentView(CustomView);
        mDialog.setCanceledOnTouchOutside(false);
        Window dialogWindow = mDialog.getWindow();
        assert dialogWindow != null;
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (ScreenUtils.getScreenWidth(mContext) * 0.80); // 宽度设置为屏幕的0.80
        dialogWindow.setAttributes(p);

        //点击屏幕外侧，dialog不消失
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }

    /**
     * 通用对话框（包括 内容、确定和 标题）
     *
     * @param mContext
     * @param messageStr
     * @param confirmStr
     */
    public void setDialogTitleAndMessage(Context mContext, String messageStr, String confirmStr, String titleStr) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View CustomView;
        if (titleStr != null) {
            CustomView = inflater.inflate(R.layout.custom_dialog_message_title, null);
            TextView title = (TextView) CustomView.findViewById(R.id.tv_custom_dialog_setting_title);
            title.setText(titleStr);
        } else {
            CustomView = inflater.inflate(R.layout.custom_dialog_update, null);
        }

        TextView message = (TextView) CustomView.findViewById(R.id.tv_custom_dialog_setting_message);
        TextView confirm = (TextView) CustomView.findViewById(R.id.tv_custom_dialog_setting_confirm);

        if (null != messageStr) {
            message.setText(messageStr);
        }
        if (null != confirmStr) {
            confirm.setText(confirmStr);
        }


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onSaveClick();
                }
                dimissDialog();
            }
        });

        mDialog = new Dialog(mContext, R.style.Theme_AudioDialog);
        mDialog.setContentView(CustomView);
        mDialog.setCanceledOnTouchOutside(false);
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (ScreenUtils.getScreenWidth(mContext) * 0.80); // 宽度设置为屏幕的0.80
        dialogWindow.setAttributes(p);

        //点击屏幕外侧，dialog不消失
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }

    /**
     * 通用对话框（包括 内容、确定和 标题）
     *
     * @param mContext
     * @param messageStr
     * @param confirmStr
     */
    public void setDialogTitleAndLongMessage(Context mContext, String messageStr, String confirmStr, String titleStr) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View CustomView;
        if (titleStr != null) {
            CustomView = inflater.inflate(R.layout.custom_dialog_long_message_title, null);
            TextView title = (TextView) CustomView.findViewById(R.id.tv_custom_dialog_setting_title);
            title.setText(titleStr);
        } else {
            CustomView = inflater.inflate(R.layout.custom_dialog_update, null);
        }

        TextView message = (TextView) CustomView.findViewById(R.id.tv_custom_dialog_setting_message);
        TextView confirm = (TextView) CustomView.findViewById(R.id.tv_custom_dialog_setting_confirm);

        if (null != messageStr) {
            message.setText(messageStr);
        }
        if (null != confirmStr) {
            confirm.setText(confirmStr);
        }


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onSaveClick();
                }
                dimissDialog();
            }
        });

        mDialog = new Dialog(mContext, R.style.Theme_AudioDialog);
        mDialog.setContentView(CustomView);
        mDialog.setCanceledOnTouchOutside(false);
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (ScreenUtils.getScreenWidth(mContext) * 0.80); // 宽度设置为屏幕的0.80
        dialogWindow.setAttributes(p);

        //点击屏幕外侧，dialog不消失
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }

    /**
     * 通用对话框（包括内容、确定和 标题）
     *
     * @param mContext
     * @param messageStr
     * @param confirmStr
     */
    public void setDialogHint(Context mContext, String messageStr, String confirmStr, String titleStr) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View CustomView;
        if (titleStr != null) {
            CustomView = inflater.inflate(R.layout.custom_dialog_hint_title, null);
            TextView title = (TextView) CustomView.findViewById(R.id.tv_custom_dialog_setting_title);
            title.setText(titleStr);
        } else {
            CustomView = inflater.inflate(R.layout.custom_dialog_update, null);
        }

        TextView message = (TextView) CustomView.findViewById(R.id.tv_custom_dialog_setting_message);
        TextView cancel = (TextView) CustomView.findViewById(R.id.tv_custom_dialog_setting_cancel);
        TextView confirm = (TextView) CustomView.findViewById(R.id.tv_custom_dialog_setting_confirm);

        if (null != messageStr) {
            message.setText(messageStr);
        }
        if (null != confirmStr) {
            confirm.setText(confirmStr);
        }
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (null != mListener) {
                    mListener.onCancelClick();
                }
                dimissDialog();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onSaveClick();
                }
                dimissDialog();
            }
        });

        mDialog = new Dialog(mContext, R.style.Theme_AudioDialog);
        mDialog.setContentView(CustomView);
        mDialog.setCanceledOnTouchOutside(false);
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (ScreenUtils.getScreenWidth(mContext) * 0.80); // 宽度设置为屏幕的0.80
        dialogWindow.setAttributes(p);

        //点击屏幕外侧，dialog不消失
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }

    /**
     * 对话框（选择）
     *
     * @param mContext
     * @return
     */
    public void mySelectBuilder(Context mContext, final List<String> list) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View CustomView = inflater.inflate(R.layout.custom_dialog_select, null);
        ListView listView = (ListView) CustomView.findViewById(R.id.listView);
        if (null != list && list.size() > 0) {
            ListAdapter adapter = new ListAdapter(mContext);
            adapter.setData(list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (mSaveListener != null) {
                        mSaveListener.save(position, list.get(position));
                    }
                    dimissDialog();
                }
            });
        }

        mDialog = new Dialog(mContext, R.style.Theme_AudioDialog);
        mDialog.setContentView(CustomView);
        mDialog.setCanceledOnTouchOutside(false);
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (ScreenUtils.getScreenWidth(mContext) * 0.75); // 宽度设置为屏幕的0.80
        if (null != list && list.size() > 0) {
            if (list.size() > 9) {
                p.height = (int) (ScreenUtils.getScreenHeight(mContext) * 0.80); // 宽度设置为屏幕的0.80
            }
        }
        dialogWindow.setAttributes(p);
        //点击屏幕外侧，dialog不消失
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.show();
        mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (mSaveListener != null) {
                    mSaveListener.save(-1, null);
                }
            }
        });
    }

    /**
     * 对话框（加载等待）
     *
     * @param mContext
     * @return
     */
    public void showDialog(Context mContext) {
        mDialog = new Dialog(mContext, R.style.Theme_Custom_Loading);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.custom_dialog_loading, null);
        ImageView imageView1 = (ImageView) view.findViewById(R.id.loading);
        Animation rotate = AnimationUtils.loadAnimation(mContext, R.anim.loading);
        imageView1.startAnimation(rotate);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);
        /**
         * 设置loadingDialog不能被返回键销毁
         */
        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (KeyEvent.KEYCODE_BACK == keyCode) {
                    return true;
                }
                return false;
            }
        });
        mDialog.show();
    }

    /**
     * 对话框（包括 标题、内容、确定和取消,输入框）
     *
     * @param mContext
     * @return
     */
    public void mySettingAdd(Context mContext, String messageStr, String confirmStr) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View CustomView = inflater.inflate(R.layout.custom_dialog_add, null);
        TextView message = (TextView) CustomView.findViewById(R.id.tv_custom_dialog_setting_message);
        TextView cancel = (TextView) CustomView.findViewById(R.id.tv_custom_dialog_setting_cancel);
        TextView confirm = (TextView) CustomView.findViewById(R.id.tv_custom_dialog_setting_confirm);
        final EditText edit = (EditText) CustomView.findViewById(R.id.edit);
        if (null != messageStr) {
            message.setText(messageStr);
        }
        if (null != messageStr && !confirmStr.equals("")) {
            confirm.setText(confirmStr);
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (null != mListener) {
                    mListener.onCancelClick();
                }
                dimissDialog();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mSaveCancelListener) {
                    mSaveCancelListener.save(edit.getText().toString());
                }
                dimissDialog();
            }
        });

        mDialog = new Dialog(mContext, R.style.Theme_AudioDialog);
        mDialog.setContentView(CustomView);
        mDialog.setCanceledOnTouchOutside(false);
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (ScreenUtils.getScreenWidth(mContext) * 0.75); // 宽度设置为屏幕的0.80
        dialogWindow.setAttributes(p);

        //点击屏幕外侧，dialog不消失
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }


    /**
     * 销毁对话框
     */
    public void dimissDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }


}

