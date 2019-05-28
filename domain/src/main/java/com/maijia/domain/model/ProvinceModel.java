package com.maijia.domain.model;

import java.util.List;

/**
 * 省分信息
 */
public class ProvinceModel {
	private String areaId;
	private String areaName;
	private List<CityModel> aearList;

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

	public List<CityModel> getAearList() {
		return aearList;
	}

	public void setAearList(List<CityModel> aearList) {
		this.aearList = aearList;
	}
}
