package pl.agol.cerberus.mapper;

import org.springframework.jdbc.core.RowMapper;
import pl.agol.cerberus.core.Expectation;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpectationMapper implements RowMapper<Expectation>, Serializable {

    @Override
    public Expectation mapRow(ResultSet rs, int i) throws SQLException {
        return new Expectation(rs.getString("path"), rs.getString("value"));
    }
}