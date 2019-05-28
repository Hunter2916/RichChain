package com.maijia.rc.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maijia.domain.model.MillStoreData;
import com.maijia.rc.R;
import com.maijia.rc.databinding.MillStoreRecycleItemBinding;
import com.maijia.rc.listener.OnBack1Listener;

import java.util.List;

/**
 * Created by zhaoliang on 2018/6/19 0019
 */

public class MillStoreAdapter extends RecyclerView.Adapter<MillStoreAdapter.ViewHolder> {
    MillStoreRecycleItemBinding binding;
    private List<MillStoreData> dataList;
    private Context context;
    private LayoutInflater inflater;

    public MillStoreAdapter(List<MillStoreData> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 创建hodler对象
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(inflater, R.layout.mill_store_recycle_item, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        MillStoreData millStoreData = dataList.get(position);
        binding.image.setImageResource(millStoreData.getImageId());
        binding.name.setText(millStoreData.getMillName());
        binding.hashrate.setText(String.valueOf(millStoreData.getHashRate()+"G"));
        binding.percent.setText(millStoreData.getPercent() + "%");
        binding.price.setText(String.valueOf(millStoreData.getPrice()) + "RMB");
        binding.time.setText(String.valueOf(millStoreData.getTime()) + "小时");
        binding.output.setText(String.valueOf(millStoreData.getOutput()) + "个/小时");

        binding.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.bank1(position);
                }
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

    private OnBack1Listener mListener;

    public void setOnBack1Listener(OnBack1Listener mListener) {
        this.mListener = mListener;
    }
}
