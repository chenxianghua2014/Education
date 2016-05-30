/*
 * 2015年02月05日
 * 董再兴
 * 为了使用postgresql，使用表主键uuid类型
 */
package com.ttgis.education.utils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * 
 * @author dongzaixing
 */
public class UUIDTypeHandler extends BaseTypeHandler {

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return UUID.fromString(rs.getString(columnName));
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return UUID.fromString((rs.getString(columnIndex)));
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return UUID.fromString((cs.getString(columnIndex)));
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jt) throws SQLException {
        ps.setString(i, ((UUID) parameter).toString());
    }
    
}
