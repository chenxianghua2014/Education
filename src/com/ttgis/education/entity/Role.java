package com.ttgis.education.entity;

import java.util.Date;

public class Role {
    private String roleId;

    private String roleName;

    private Date roleAt;

    private Integer roleDel;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getRoleAt() {
        return roleAt;
    }

    public void setRoleAt(Date roleAt) {
        this.roleAt = roleAt;
    }

    public Integer getRoleDel() {
        return roleDel;
    }

    public void setRoleDel(Integer roleDel) {
        this.roleDel = roleDel;
    }
}