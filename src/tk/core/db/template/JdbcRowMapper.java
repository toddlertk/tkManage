package tk.core.db.template;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class JdbcRowMapper implements RowMapper{
	
	public static final JdbcRowMapper SCALAR = new JdbcRowMapper();

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		return rs.getObject(1);
	}

}
