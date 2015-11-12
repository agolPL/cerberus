package pl.agol.cerberus.mapper;

import org.springframework.jdbc.core.RowMapper;
import pl.agol.cerberus.core.TestScenario;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestScenarioMapper implements RowMapper<TestScenario>, Serializable {

    @Override
    public TestScenario mapRow(ResultSet rs, int i) throws SQLException {
        return new TestScenario()
                .setNumber(rs.getLong("number"))
                .setName(rs.getString("name"))
                .setDescription(rs.getString("description"))
                .setEnable(rs.getBoolean("enable"))
                .setSetupScript(rs.getString("setup_script"))
                .setScenarioScript(rs.getString("scenario_script"))
                .setCleanupScript(rs.getString("cleanup_script"));
    }
}
