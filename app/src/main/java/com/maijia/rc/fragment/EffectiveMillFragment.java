package com.maijia.rc.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maijia.domain.model.EffectiveMillData;
import com.maijia.rc.R;
import com.maijia.rc.adapter.EffectiveMillAdapter;
import com.maijia.rc.databinding.FragmentEffectiveMillBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 有效矿机数
 */
public class EffectiveMillFragment extends BaseFragment {
    private List<EffectiveMillData> effectiveMillDataList;
    FragmentEffectiveMillBinding binding;
    private EffectiveMillAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_effective_mill, container, false);
        initClick();
        initView();
        return binding.getRoot();
    }

    private void initView() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        effectiveMillDataList = new ArrayList<>();
        effectiveMillDataList.add(new EffectiveMillData(R.mipmap.ic_min_mill, "微型矿机", "5a8e8d5t2h", "2018-06-21 18:00:00", "2018-07-21 18:00:00", "401.328"));
        effectiveMillDataList.add(new EffectiveMillData(R.mipmap.ic_min_mill, "微型矿机", "5a8e9d5t2h", "2018-06-21 18:00:00", "2018-07-21 18:00:00", "401.328"));
        effectiveMillDataList.add(new EffectiveMillData(R.mipmap.ic_min_mill, "微型矿机", "5a8e8d9t2h", "2018-06-21 18:00:00", "2018-07-21 18:00:00", "401.328"));
        effectiveMillDataList.add(new EffectiveMillData(R.mipmap.ic_min_mill, "微型矿机", "5a8e8d5t2h", "2018-06-21 18:00:00", "2018-07-21 18:00:00", "401.328"));
        effectiveMillDataList.add(new EffectiveMillData(R.mipmap.ic_min_mill, "微型矿机", "5a8e8d5t2h", "2018-06-21 18:00:00", "2018-07-21 18:00:00", "401.328"));
        effectiveMillDataList.add(new EffectiveMillData(R.mipmap.ic_min_mill, "微型矿机", "5a8e8d3t2h", "2018-06-21 18:00:00", "2018-07-21 18:00:00", "401.328"));
        effectiveMillDataList.add(new EffectiveMillData(R.mipmap.ic_min_mill, "微型矿机", "5a8e1d5t2h", "2018-06-21 18:00:00", "2018-07-21 18:00:00", "401.328"));
        effectiveMillDataList.add(new EffectiveMillData(R.mipmap.ic_min_mill, "微型矿机", "5a8e7d5t2h", "2018-06-21 18:00:00", "2018-07-21 18:00:00", "401.328"));
        adapter = new EffectiveMillAdapter(effectiveMillDataList, getActivity());
        binding.recyclerView.setAdapter(adapter);
    }

    private void initClick() {

    }

    @Override
    public void onClick(View v) {

    }
}
