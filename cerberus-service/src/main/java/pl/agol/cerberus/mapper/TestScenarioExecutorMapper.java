package pl.agol.cerberus.mapper;

import org.springframework.jdbc.core.RowMapper;
import pl.agol.cerberus.ClassLoaderService;
import pl.agol.cerberus.TestScenarioExecutor;
import pl.agol.cerberus.core.ScriptExecutorFactory;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestScenarioExecutorMapper implements RowMapper<TestScenarioExecutor>, Serializable {

    private final ClassLoaderService classLoaderService;

    public TestScenarioExecutorMapper(ClassLoaderService classLoaderService) {
        this.classLoaderService = classLoaderService;
    }

    @Override
    public TestScenarioExecutor mapRow(ResultSet rs, int i) throws SQLException {
        return new TestScenarioExecutor()
                .setSetupScriptExecutorFactoryClass(createClass(rs.getString("setup_script_executor_factory_class")))
                .setScenarioScriptExecutorFactoryClass(createClass(rs.getString("scenario_script_executor_factory_class")))
                .setCleanupScriptExecutorFactoryClass(createClass(rs.getString("cleanup_script_executor_factory_class")));
    }

    private Class<ScriptExecutorFactory> createClass(String scriptClassName) {
        try {
            return classLoaderService.loadClass(scriptClassName, ScriptExecutorFactory.class);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}