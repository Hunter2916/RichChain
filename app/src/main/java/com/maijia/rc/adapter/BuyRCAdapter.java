package com.maijia.rc.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.maijia.domain.model.BuyRCData;
import com.maijia.rc.R;
import com.maijia.rc.databinding.BuyRichChainLvItemBinding;
import com.maijia.rc.listener.OnBack1Listener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoliang on 2018/7/10 0010
 */

public class BuyRCAdapter extends BaseAdapter {
    private List<BuyRCData> list = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    private BuyRCData data;
    BuyRichChainLvItemBinding binding;

    public BuyRCAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 传入数据入口
     *
     * @return
     */
    public void setData(List<BuyRCData> list) {
        if (list != null) {
            this.list = list;
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        data = list.get(position);
        if (convertView == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.buy_rich_chain_lv_item, parent, false);
            //布局文件中与绑定相关联的最外层视图
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (BuyRichChainLvItemBinding) convertView.getTag();
        }
        binding.image.setImageResource(data.getImageId());
        binding.name.setText(data.getNickName());
        binding.quantity.setText(String.valueOf(data.getQuantity()));
        binding.price.setText(String.valueOf(data.getPrice()));
        binding.deal.setText(String.valueOf(data.getDeal()));
        binding.sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.bank1(position);
                }
            }
        });
        return convertView;
    }

    private OnBack1Listener mListener;

    public void setOnBack1Listener(OnBack1Listener mListener) {
        this.mListener = mListener;
    }

}
