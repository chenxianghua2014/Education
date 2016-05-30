package com.ttgis.education.entity;

import java.util.Date;

public class Dictionary {
    private String dictionaryId;//数据字典ID

    private String dictionaryIndex;//指标项

    private String dictionaryCodeset;//代码集
    
    private String dictionaryCode;//代码

    private String dictionaryDescribe;//代码描述

    private Date dictionaryAt;//添加时间

    private Integer dictionaryDel;  //删除标记

    public String getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(String dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public String getDictionaryIndex() {
        return dictionaryIndex;
    }

    public void setDictionaryIndex(String dictionaryIndex) {
        this.dictionaryIndex = dictionaryIndex;
    }

    public String getDictionaryCodeset() {
        return dictionaryCodeset;
    }

    public void setDictionaryCodeset(String dictionaryCodeset) {
        this.dictionaryCodeset = dictionaryCodeset;
    }

    public String getDictionaryCode() {
        return dictionaryCode;
    }

    public void setDictionaryCode(String dictionaryCode) {
        this.dictionaryCode = dictionaryCode;
    }

    public String getDictionaryDescribe() {
        return dictionaryDescribe;
    }

    public void setDictionaryDescribe(String dictionaryDescribe) {
        this.dictionaryDescribe = dictionaryDescribe;
    }

    public Date getDictionaryAt() {
        return dictionaryAt;
    }

    public void setDictionaryAt(Date dictionaryAt) {
        this.dictionaryAt = dictionaryAt;
    }

    public Integer getDictionaryDel() {
        return dictionaryDel;
    }

    public void setDictionaryDel(Integer dictionaryDel) {
        this.dictionaryDel = dictionaryDel;
    }
}