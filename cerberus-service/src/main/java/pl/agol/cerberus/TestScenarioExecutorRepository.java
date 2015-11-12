package pl.agol.cerberus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.agol.cerberus.core.Expectation;
import pl.agol.cerberus.core.TestScenario;
import pl.agol.cerberus.mapper.ExpectationMapper;
import pl.agol.cerberus.mapper.TestScenarioExecutorMapper;
import pl.agol.cerberus.mapper.TestScenarioMapper;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
public class TestScenarioExecutorRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ClassLoaderService classLoaderService;

    @Autowired
    public TestScenarioExecutorRepository(
            JdbcTemplate jdbcTemplate, ClassLoaderService classLoaderService) {

        this.jdbcTemplate = jdbcTemplate;
        this.classLoaderService = classLoaderService;
    }

    public List<TestScenarioExecutor> getAllTestScenarioExecutors() {

        List<TestScenario> testScenarios = getAllScenarions();
        testScenarios.forEach(this::assignExpectations);
        return testScenarios.stream()
                .map(this::findAndCreateTestExecutor)
                .collect(toList());
    }

    private List<TestScenario> getAllScenarions() {
        return jdbcTemplate.query("select * from test_scenario", new TestScenarioMapper());
    }

    private void assignExpectations(TestScenario testScenario) {
        List<Expectation> expectations =
                jdbcTemplate.query("select * from expectation where test_scenario_number = ?",
                        new ExpectationMapper(), testScenario.getNumber());
        testScenario.setExceptions(expectations);
    }


    private TestScenarioExecutor findAndCreateTestExecutor(TestScenario testScenario) {
        TestScenarioExecutor testScenarioExecutor =
                jdbcTemplate.queryForObject("select * from test_scenario_executor_info e join test_scenario s " +
                                "on e.id = s.test_scenario_executor_info_id where s.number = ?",
                        new Object[]{testScenario.getNumber()}, new TestScenarioExecutorMapper(classLoaderService));

        if (testScenarioExecutor != null) {
            testScenarioExecutor.setTestScenario(testScenario);
            return testScenarioExecutor;
        } else {
            throw new RuntimeException();
        }
    }

    public TestScenarioExecutor fetchTestScenarioExecutor(long scenarioNumber) {
        TestScenario testScenario = fetchScenarioByNumber(scenarioNumber);
        assignExpectations(testScenario);
        return findAndCreateTestExecutor(testScenario);
    }

    private TestScenario fetchScenarioByNumber(long scenarioNumber) {
        return jdbcTemplate.queryForObject("select * from test_scenario s where s.number = ?",
                new Object[]{scenarioNumber}, new TestScenarioMapper());
    }
}
