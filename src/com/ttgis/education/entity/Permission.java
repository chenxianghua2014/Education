package com.ttgis.education.entity;

import java.util.Date;

public class Permission {
    private String permissionId;

    private String roleId;

    private String permissionName;

    private String permissionJson;

    private Date permissionAt;

    private Integer permissionDel;

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionJson() {
        return permissionJson;
    }

    public void setPermissionJson(String permissionJson) {
        this.permissionJson = permissionJson;
    }

    public Date getPermissionAt() {
        return permissionAt;
    }

    public void setPermissionAt(Date permissionAt) {
        this.permissionAt = permissionAt;
    }

    public Integer getPermissionDel() {
        return permissionDel;
    }

    public void setPermissionDel(Integer permissionDel) {
        this.permissionDel = permissionDel;
    }
}