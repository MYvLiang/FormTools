package com.formtools.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author myl
 * @create 2020-04-14  11:24
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes({JSONObject.class})
public class JSONObjectTypeHandler extends BaseTypeHandler<JSONObject> {

    /**
     * 将JSONObject存入数据库
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JSONObject parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.toJSONString());
    }

    /**
     * 把字符串取出转换为JSONObject
     */
    @Override
    public JSONObject getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        if (null != json) {
            return JSONObject.parseObject(json);
        }
        return null;
    }

    /**
     * 把字符串取出转换为JSONObject
     */
    @Override
    public JSONObject getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex);
        if (null != json) {
            return JSONObject.parseObject(json);
        }
        return null;
    }

    /**
     * 把字符串取出转换为JSONObject
     */
    @Override
    public JSONObject getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex);
        if (null != json) {
            return JSONObject.parseObject(json);
        }
        return null;
    }
}
