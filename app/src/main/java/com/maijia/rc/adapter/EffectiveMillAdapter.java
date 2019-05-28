package com.maijia.rc.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maijia.domain.model.EffectiveMillData;
import com.maijia.rc.R;
import com.maijia.rc.databinding.EffectiveMillRecycleItemBinding;

import java.util.List;

/**
 * Created by zhaoliang on 2018/6/19 0019
 */

public class EffectiveMillAdapter extends RecyclerView.Adapter<EffectiveMillAdapter.ViewHolder> {
    EffectiveMillRecycleItemBinding binding;
    private List<EffectiveMillData> dataList;
    private Context context;
    private LayoutInflater inflater;

    public EffectiveMillAdapter(List<EffectiveMillData> dataList, Context context) {
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
        binding = DataBindingUtil.inflate(inflater, R.layout.effective_mill_recycle_item, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        EffectiveMillData effectiveMillData = dataList.get(position);
        binding.image.setImageResource(effectiveMillData.getImageId());
        binding.name.setText(effectiveMillData.getMillName());
        binding.number.setText(effectiveMillData.getNumber());
        binding.creatTime.setText(effectiveMillData.getCreateTime());
        binding.endTime.setText(effectiveMillData.getEndTime());
        binding.income.setText(effectiveMillData.getInCome()+"个");
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

//    private OnBack1Listener mListener;
//
//    public void setOnBack1Listener(OnBack1Listener mListener) {
//        this.mListener = mListener;
//    }
}
