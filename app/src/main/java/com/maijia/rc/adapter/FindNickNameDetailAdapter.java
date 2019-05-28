package com.maijia.rc.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maijia.data.util.ToastUtil;
import com.maijia.domain.model.FindNickNameDetailData;
import com.maijia.rc.R;
import com.maijia.rc.databinding.FindNickNameRecycleItemBinding;

import java.util.List;

public class FindNickNameDetailAdapter extends RecyclerView.Adapter<FindNickNameDetailAdapter.ViewHolder> {
    FindNickNameRecycleItemBinding binding;
    private List<FindNickNameDetailData> dataList;
    private Context context;
    private LayoutInflater inflater;

    public FindNickNameDetailAdapter(List<FindNickNameDetailData> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 创建布局对象
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(inflater, R.layout.find_nick_name_recycle_item, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final FindNickNameDetailData data = dataList.get(position);
        binding.nickName.setText(data.getNickName());
        binding.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setPrimaryClip(ClipData.newPlainText("text", data.getNickName()));
                if (cm.hasPrimaryClip()) {
                    cm.getPrimaryClip().getItemAt(0).getText();
                }
                ToastUtil.show(context, "复制成功");
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
