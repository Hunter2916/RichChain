package com.maijia.rc.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maijia.data.util.ToastUtil;
import com.maijia.domain.model.MillStoreData;
import com.maijia.rc.R;
import com.maijia.rc.adapter.MillStoreAdapter;
import com.maijia.rc.databinding.FragmentMillStoreBinding;
import com.maijia.rc.dialog.DialogManager;
import com.maijia.rc.listener.OnBack1Listener;
import com.maijia.rc.listener.OnSaveCancelListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 矿机商城
 */
public class MillStoreFragment extends BaseFragment {
    private List<MillStoreData> millStoreDataList;
    FragmentMillStoreBinding binding;
    private MillStoreAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mill_store, container, false);
        initView();
        initClick();
        return binding.getRoot();
    }

    /**
     * 初始化数据
     */
    private void initView() {
        //设置recyclerview 的管理器
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置横向布局：
//        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        //设置网格布局：
//        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        //设置瀑布流：
//        binding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        millStoreDataList = new ArrayList<>();
        MillStoreData superMill = new MillStoreData(R.mipmap.ic_super_mill, "超级矿机", 100000, 10, "30", 1440, 90.278);
        millStoreDataList.add(superMill);
        MillStoreData bigMill = new MillStoreData(R.mipmap.ic_big_mill, "大型矿机", 50000, 5, "30", 1440, 45.139);
        millStoreDataList.add(bigMill);
        MillStoreData middleMill = new MillStoreData(R.mipmap.ic_middle_mill, "中型矿机", 10000, 1, "30", 1440, 9.0278);
        millStoreDataList.add(middleMill);
        MillStoreData smallMill = new MillStoreData(R.mipmap.ic_small_mill, "小型矿机", 5000, 0.5, "30", 1440, 4.514);
        millStoreDataList.add(smallMill);
        MillStoreData minMill = new MillStoreData(R.mipmap.ic_min_mill, "微型矿机", 1000, 0.1, "30", 1440, 0.929);
        millStoreDataList.add(minMill);
        adapter = new MillStoreAdapter(millStoreDataList, getActivity());
        binding.recyclerView.setAdapter(adapter);
    }

    /**
     * 回调方法
     */
    private void initClick() {
        adapter.setOnBack1Listener(new OnBack1Listener() {
            @Override
            public void bank1(int postion) {
//                ToastUtil.show(getActivity(), "你点击的是：" + millStoreDataList.get(postion).getMillName());
                if (millStoreDataList != null && millStoreDataList.size() > 0) {
//                    BuyMillDetailActivity.startAction(getActivity(), millStoreDataList.get(postion));
                    final DialogManager dialogManager = new DialogManager();
                    dialogManager.mySettingAdd(getActivity(), "交易密码", "确定");
                    dialogManager.setOnSaveCancelListener(new OnSaveCancelListener() {
                        @Override
                        public void save(String value) {
                            ToastUtil.show(getActivity(), "你输入的是：" + value);
                        }

                        @Override
                        public void cancel() {

                        }
                    });
                }
            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}
