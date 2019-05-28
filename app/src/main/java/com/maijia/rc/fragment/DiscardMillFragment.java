package com.maijia.rc.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maijia.domain.model.EffectiveMillData;
import com.maijia.rc.R;
import com.maijia.rc.adapter.EffectiveMillAdapter;
import com.maijia.rc.databinding.FragmentDiscardMillBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscardMillFragment extends BaseFragment {
    FragmentDiscardMillBinding binding;
    List<EffectiveMillData> effectiveMillDataList;
    private EffectiveMillAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_discard_mill, container, false);
        initClick();
        initView();
        return binding.getRoot();
    }

    private void initView() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        effectiveMillDataList = new ArrayList<>();
        effectiveMillDataList.add(new EffectiveMillData(R.mipmap.ic_min_mill, "微型矿机", "5a8e8d5t2h", "2018-04-21 18:00:00", "2018-05-21 18:00:00", "401.328"));
        adapter = new EffectiveMillAdapter(effectiveMillDataList, getActivity());
        binding.recyclerView.setAdapter(adapter);

    }

    private void initClick() {

    }

    @Override
    public void onClick(View v) {

    }
}
