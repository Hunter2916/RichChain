package com.maijia.rc.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maijia.rc.R;
import com.maijia.rc.databinding.FragmentTeamBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFragment extends BaseFragment {
    FragmentTeamBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_team, container, false);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {

    }
}
