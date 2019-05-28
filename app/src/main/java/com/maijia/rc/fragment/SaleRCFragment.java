package com.maijia.rc.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maijia.rc.R;
import com.maijia.rc.databinding.FragmentSaleRcBinding;

/**
 * 卖出RC
 */
public class SaleRCFragment extends BaseFragment {
    FragmentSaleRcBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sale_rc, container, false);
        initClick();
        return binding.getRoot();
    }

    private void initClick() {

    }

    @Override
    public void onClick(View v) {

    }
}
