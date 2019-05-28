package com.maijia.domain.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/5/21.
 */
public class FlowLayoutBean {

    /**
     * 唯一编号
     */
    private int id;
    /**
     * 名称
     */
    private String columnName;
    /**
     * id
     */
    private int templateConfigId;
    /**
     * 预选值
     */
    private String columnRemake;
    /**
     * 排序
     */
    private String sort;
    /**
     * 选中值
     */
    private String value;

    private List<String> defaultValue;
    private List<Integer> defaultValueId;

    /**
     * 选中值集合  方便取值判断
     */
    private Set<String> select = new HashSet();

    public List<Integer> getDefaultValueId() {
        return defaultValueId;
    }

    public void setDefaultValueId(List<Integer> defaultValueId) {
        this.defaultValueId = defaultValueId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnRemake() {
        return columnRemake;
    }

    public void setColumnRemake(String columnRemake) {
        this.columnRemake = columnRemake;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getTemplateConfigId() {
        return templateConfigId;
    }

    public void setTemplateConfigId(int templateConfigId) {
        this.templateConfigId = templateConfigId;
    }

    public Set<String> getSelect() {
        return select;
    }

    public void setSelect(Set<String> select) {
        this.select = select;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<String> getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(List<String> defaultValue) {
        this.defaultValue = defaultValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
