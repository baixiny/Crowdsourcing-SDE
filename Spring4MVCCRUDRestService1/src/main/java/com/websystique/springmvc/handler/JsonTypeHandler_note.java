package com.websystique.springmvc.handler;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONObject;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.postgresql.util.PGobject;

@MappedJdbcTypes(JdbcType.OTHER) // 可有可无
@MappedTypes(JSONObject.class)

	public  class  JsonTypeHandler_note extends BaseTypeHandler<Object> {
	    private static final PGobject jsonObject = new PGobject();
	    @Override
	    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
	        jsonObject.setType("json");
	        jsonObject.setValue(parameter.toString());
	        ps.setObject(i, jsonObject);
	    }

	    @Override
	    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
	        return rs.getString(columnIndex);
	    }

	    @Override
	    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
	        return cs.getString(columnIndex);
	    }

	    @Override
	    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
	        return rs.getString(columnName);
	    }

	}


