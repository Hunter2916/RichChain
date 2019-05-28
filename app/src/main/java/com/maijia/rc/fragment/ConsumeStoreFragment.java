package com.maijia.rc.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maijia.rc.R;
import com.maijia.rc.databinding.FragmentConsumeStoreBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsumeStoreFragment extends BaseFragment {
    FragmentConsumeStoreBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_consume_store, container, false);
        initView();
        initClick();
        return binding.getRoot();
    }

    private void initClick() {

    }

    private void initView() {

    }

    @Override
    public void onClick(View v) {

    }
}
