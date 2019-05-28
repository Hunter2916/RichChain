package com.maijia.rc.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.maijia.domain.model.CityModel;
import com.maijia.domain.model.DistrictModel;
import com.maijia.domain.model.ProvinceModel;
import com.maijia.rc.R;
import com.maijia.rc.utils.JsonParserUtils;
import com.maijia.rc.view.wheelview.OnWheelChangedListener;
import com.maijia.rc.view.wheelview.WheelView;
import com.maijia.rc.view.wheelview.adapter.ArrayWheelAdapter;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class AddressSelectPopw extends PopupWindow {
    //所有省
    private String[] mProvinceDatas = null;
    //key - 省 value - 市
    private Map<String, String[]> mCitisDatasMap = new IdentityHashMap<>();
    private String[] mCitisString = null;
    List<ProvinceModel> provinceList = null;
    //当前省的名称
    private String mCurrentProviceName;
    private String mCurrentProviceId;
    //当前市的名称
    private String mCurrentCityName;
    private String mCurrentCityId;
    //当前区的名称
    private String mCurrentDistrictName;
    private String mCurrentDistrictId;
    //key  市    value  区
    private Map<String, String[]> mDistrictsDatasMap = new IdentityHashMap<>();
    private String[] mDistrictsString = null;
    //存放地区编号
    private String[] mDistrictsIDString = null;
    //存放区地名和编号
    private Map<String, String[]> mDistrictsIDMap = new IdentityHashMap<>();
    private TextView close, finish;
    private View mMenuView;

    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;

    private Context mContext;
    private String[] cityNames;
    private List<CityModel> cityList;


    public AddressSelectPopw(Context context) {
        super(context);
        mContext = context;
        initProvinceDatas();
        initWindow();
    }


    private void initWindow() {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.custom_address_select, null);

        close = (TextView) mMenuView.findViewById(R.id.close);
        finish = (TextView) mMenuView.findViewById(R.id.finish);

        mViewProvince = (WheelView) mMenuView.findViewById(R.id.province);
        mViewCity = (WheelView) mMenuView.findViewById(R.id.city);
        mViewDistrict = (WheelView) mMenuView.findViewById(R.id.district);
        lightOff();

        // 设置按钮监听
        close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                lightOn();
                dissmissPopWindow(mContext);
            }
        });
        finish.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                lightOn();
                if (null != mListener) {
                    mListener.onAddress(mCurrentProviceName, mCurrentCityName, mCurrentDistrictName, mCurrentProviceId, mCurrentCityId, mCurrentDistrictId);
                }
                dissmissPopWindow(mContext);

            }
        });

        mViewProvince.setViewAdapter(new ArrayWheelAdapter<>(mContext, mProvinceDatas));
        // 设置可见条目数量
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);
        updateCities();
        updateDistricts();

        mViewProvince.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                updateCities();
                updateDistricts();
            }
        });
        mViewCity.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                if (null != mCitisString) {
                    if (mCitisString.length == 0) {
                        mCurrentCityName = "";
                        mCurrentCityId = "";
                    } else {
                        mCurrentCityName = mCitisString[mViewCity.getCurrentItem()];
                        mCurrentCityId = provinceList.get(mViewProvince.getCurrentItem()).getAearList().get(mViewCity.getCurrentItem()).getAreaId();
                    }
                    updateDistricts();
                }
            }
        });
        mViewDistrict.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                if (null != mDistrictsString) {
                    if (mDistrictsString.length == 0) {
                        mCurrentDistrictName = "";
                        mCurrentDistrictId = "";
                    } else {
                        mCurrentDistrictName = mDistrictsString[mViewDistrict.getCurrentItem()];
                        mCurrentDistrictId = mDistrictsIDString[mViewDistrict.getCurrentItem()];
//                        ToastUtil.show(mContext, "区的ID为：：" + mCurrentDistrictId);
                    }
                }
            }
        });
        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        this.setClippingEnabled(false);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.FILL_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.popupAnimation);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    /**
     * 根据当前的市，更新区  WheelView的信息
     */
    private void updateDistricts() {
        int pCurrent = mViewCity.getCurrentItem();
//        mCurrentCityName = cityNames[pCurrent];
//        ToastUtil.show(mContext, "省份的名称：：" + mCurrentCityName);
//        mCurrentCityId = cityList.get(pCurrent).getAreaId();
        if (null == mDistrictsDatasMap) {
            return;
        }
        mDistrictsString = mDistrictsDatasMap.get(mCurrentCityName);
        mDistrictsIDString = mDistrictsIDMap.get(mCurrentCityName);
        if (null != mDistrictsString && mDistrictsIDString != null) {
            if (mDistrictsString.length == 0) {
                mCurrentDistrictName = "";
                mCurrentDistrictId = "";
            } else {
                mCurrentDistrictName = mDistrictsString[0];
                mCurrentDistrictId = mDistrictsIDString[0];
//                ToastUtil.show(mContext, "区的ID为：：" + mCurrentDistrictId);
//                mCurrentDistrictId = provinceList.get(mViewProvince.getCurrentItem()).getAearList().get(mViewCity.getCurrentItem()).getAearList().get(mViewDistrict.getCurrentItem()).getAreaId();
            }

            mViewDistrict.setViewAdapter(new ArrayWheelAdapter<>(mContext, mDistrictsString));
            mViewDistrict.setCurrentItem(0);

        }

    }

    /**
     * 根据当前的省，更新市   WheelView的信息
     */
    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
//        ToastUtil.show(mContext, "省份的名称：："+mCurrentProviceName);
        mCurrentProviceId = provinceList.get(pCurrent).getAreaId();
        if (null == mCitisDatasMap) {
            return;
        }
        mCitisString = mCitisDatasMap.get(mCurrentProviceName);
//        ToastUtil.show(mContext, Arrays.toString(mCitisString));
        if (null != mCitisString) {
            if (mCitisString.length == 0) {
                mCurrentCityName = "";
                mCurrentCityId = "";
            } else {
                mCurrentCityName = mCitisString[0];
                mCurrentCityId = provinceList.get(pCurrent).getAearList().get(0).getAreaId();
            }
            mViewCity.setViewAdapter(new ArrayWheelAdapter<>(mContext, mCitisString));
            mViewCity.setCurrentItem(0);
        }
    }

    //解析省市区的XML数据
    private void initProvinceDatas() {
        provinceList = JsonParserUtils.readFromAssets(mContext);
        //*/ 初始化默认选中的省、市
        if (provinceList != null && !provinceList.isEmpty()) {
            mCurrentProviceName = provinceList.get(0).getAreaName();
//            System.out.println("mCurrentProviceName:" + mCurrentProviceName);
            cityList = provinceList.get(0).getAearList();
            //初始化市区
            if (cityList != null && !cityList.isEmpty()) {
                mCurrentCityName = cityList.get(0).getAreaName();
                List<DistrictModel> districtList = cityList.get(0).getAearList();
                //初始化区县
                if (districtList != null && !districtList.isEmpty()) {
                    mCurrentDistrictName = districtList.get(0).getAreaName();

                }

            }
        }
        mProvinceDatas = new String[provinceList.size()];
        for (int i = 0; i < provinceList.size(); i++) {
            // 遍历所有省的数据
            mProvinceDatas[i] = provinceList.get(i).getAreaName();
            List<CityModel> cityList = provinceList.get(i).getAearList();

            if (cityList == null) {
                mCitisDatasMap.put(provinceList.get(i).getAreaName(), new String[0]);
                continue;
            }
            cityNames = new String[cityList.size()];

            for (int j = 0; j < cityList.size(); j++) {
                cityNames[j] = cityList.get(j).getAreaName();

//                if (cityNames[j] != null && !cityNames[j].isEmpty()) {
                List<DistrictModel> districtList = cityList.get(j).getAearList();
                if (districtList == null) {
                    mDistrictsDatasMap.put(cityList.get(j).getAreaName(), new String[0]);
                    continue;
                }
                String[] districtNames = new String[districtList.size()];
                String[] districtIDs = new String[districtList.size()];
                for (int k = 0; k < districtList.size(); k++) {

                    districtNames[k] = districtList.get(k).getAreaName();
                    districtIDs[k] = districtList.get(k).getAreaId();
                }

                mDistrictsIDMap.put(cityList.get(j).getAreaName(), districtIDs);
                mDistrictsDatasMap.put(cityList.get(j).getAreaName(), districtNames);

//                }

//                mDistrictsDatasMap.put(provinceList.get(i).getAearList().get(j).getAearList().get(k).getAreaName(), new String[0]);
//                ToastUtil.show(mContext, "市信息：：" + cityList.get(j).getAreaName() + "  区信息：：" + districtNames.length);
            }
            // 省-市的数据，保存到mCitisDatasMap
            mCitisDatasMap.put(provinceList.get(i).getAreaName(), cityNames);
//            ToastUtil.show(mContext, "市信息：：" + provinceList.get(i).getAreaName() + "  区信息：：" + cityNames.length);
        }
    }


    public interface OnAddressListener {
        //        void onAddress(String ProviceName, String CityName, String ProviceId, String CityId);
        void onAddress(String ProviceName, String CityName, String DistrictName, String ProviceId, String CityId, String DistrictId);

    }

    public OnAddressListener mListener;

    public void setOnAddressListener(OnAddressListener mListener) {
        this.mListener = mListener;
    }

    /**
     * 内容区域变亮
     */
    private void lightOn() {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
        lp.alpha = 1.0f;
        ((Activity) mContext).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ((Activity) mContext).getWindow().setAttributes(lp);
    }

    private void lightOff() {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
        lp.alpha = 0.4f;
        ((Activity) mContext).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ((Activity) mContext).getWindow().setAttributes(lp);
    }


    public void dissmissPopWindow(Context context) {
        if (isShowing()) {
            dismiss();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        lightOn();
    }
}