package com.hurenjieee.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * RowMapper
 * 
 * @author yanzhou
 * @version v1.0
 */
public interface RowMapper {
	public Object mapRow(ResultSet rs, int index) throws SQLException;
}