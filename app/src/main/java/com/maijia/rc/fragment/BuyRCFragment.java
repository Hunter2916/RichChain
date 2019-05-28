package com.maijia.rc.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maijia.domain.model.BuyRCData;
import com.maijia.rc.R;
import com.maijia.rc.adapter.BuyRCAdapter;
import com.maijia.rc.databinding.FragmentBuyRcBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuyRCFragment extends BaseFragment {
    private BuyRCAdapter adapter;
    FragmentBuyRcBinding binding;
    private List<BuyRCData> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_buy_rc, container, false);
//        adapter = new BuyRCAdapter(getActivity());
//        binding.listView.setAdapter(adapter);
        initClick();
        initView();
        return binding.getRoot();
    }

    private void initView() {
        list.add(new BuyRCData(R.mipmap.logo_101, "李工", 9, 0.001, 44));
        list.add(new BuyRCData(R.mipmap.logo_101, "李工", 9, 0.001, 44));
        list.add(new BuyRCData(R.mipmap.logo_101, "哈工", 9, 0.001, 5));
        list.add(new BuyRCData(R.mipmap.logo_101, "王工", 9, 0.001, 8));
        list.add(new BuyRCData(R.mipmap.logo_101, "王工", 9, 0.001, 8));
        list.add(new BuyRCData(R.mipmap.logo_101, "牛工", 8, 0.006, 9));
        list.add(new BuyRCData(R.mipmap.logo_101, "李工", 9, 0.001, 8));
        list.add(new BuyRCData(R.mipmap.logo_101, "李工", 9, 0.001, 8));
        list.add(new BuyRCData(R.mipmap.logo_101, "李工", 9, 0.001, 8));
        list.add(new BuyRCData(R.mipmap.logo_101, "和工", 19, 0.001, 8));
        list.add(new BuyRCData(R.mipmap.logo_101, "和工", 19, 0.001, 8));
        list.add(new BuyRCData(R.mipmap.logo_101, "和工", 19, 0.001, 8));
        adapter.setData(list);
    }

    private void initClick() {

    }

    @Override
    public void onClick(View v) {

    }
}
