package com.maijia.domain.model;

import java.util.List;

/**
 * 市信息
 */
public class CityModel {
    private String areaId;
    private String areaName;
    private List<DistrictModel> aearList;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public List<DistrictModel> getAearList() {
        return aearList;
    }

    public void setAearList(List<DistrictModel> aearList) {
        this.aearList = aearList;
    }
}
