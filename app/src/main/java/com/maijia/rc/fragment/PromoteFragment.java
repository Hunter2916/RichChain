package com.maijia.rc.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.maijia.rc.R;
import com.maijia.rc.activity.ShareQRCodeActivity;
import com.maijia.rc.databinding.FragmentPromoteBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromoteFragment extends BaseFragment {
    FragmentPromoteBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_promote, container, false);
        initClick();
        return binding.getRoot();
    }

    private void initClick() {
        binding.alter.setOnClickListener(this);
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.bt_A:
                        binding.btA.setChecked(true);
                        binding.btB.setChecked(false);
                        binding.btA.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                        binding.btB.setTextColor(ContextCompat.getColor(getActivity(), R.color.home_text));
                        break;
                    case R.id.bt_B:
                        binding.btA.setChecked(false);
                        binding.btB.setChecked(true);
                        binding.btB.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                        binding.btA.setTextColor(ContextCompat.getColor(getActivity(), R.color.home_text));
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.alter:
                ShareQRCodeActivity.startAction(getActivity());
                break;
        }
    }
}
