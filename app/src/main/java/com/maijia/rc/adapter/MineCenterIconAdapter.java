package com.maijia.rc.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maijia.domain.model.MineCenterIconData;
import com.maijia.rc.R;
import com.maijia.rc.databinding.MineIconRecycleItemBinding;
import com.maijia.rc.fragment.BaseFragment;

import java.util.List;

/**
 * Created by zhaoliang
 * time: 2018/6/30 0030
 * company: tenray
 */

public class MineCenterIconAdapter extends RecyclerView.Adapter<MineCenterIconAdapter.ViewHolder> {
    List<MineCenterIconData> dataList;
    private Context context;
    private LayoutInflater inflater;
    MineIconRecycleItemBinding binding;
    private BaseFragment fragment;
    private int leftRight;
    private int topBottom;
    private int spanCount;

    public MineCenterIconAdapter(List<MineCenterIconData> dataList, Context context, BaseFragment fragment, int spanCount, int leftRight, int topBottom) {
        this.dataList = dataList;
        this.context = context;
        this.fragment = fragment;
        this.spanCount = spanCount;
        this.leftRight = leftRight;
        this.topBottom = topBottom;
        inflater = LayoutInflater.from(context);
    }

    public MineCenterIconAdapter(List<MineCenterIconData> dataList, Context context) {
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
        binding = DataBindingUtil.inflate(inflater, R.layout.mine_icon_recycle_item, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MineCenterIconData data = dataList.get(position);
        //获取屏幕宽度
//        int screenWidth = fragment.getScreenWidth();
        //获取单张图片宽度
//        int itemImgWidth = (screenWidth - leftRight * (spanCount + 1)) / spanCount;
        //设置图片宽高
//        ViewGroup.LayoutParams params = binding.llItem.getLayoutParams();
//        params.width = itemImgWidth;
//        params.height = itemImgWidth;
//        binding.llItem.setLayoutParams(params);
        binding.imageView.setBackgroundResource(data.getImageId());
        binding.name.setText(data.getName());
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
