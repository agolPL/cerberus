package pl.agol.cerberus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agol.cerberus.core.TestResult;

import java.util.List;

@Service
public class TestScenarioExecutorService {

    private final TestScenarioExecutorRepository testScenarioExecutorRepository;

    @Autowired
    public TestScenarioExecutorService(TestScenarioExecutorRepository testScenarioExecutorRepository) {
        this.testScenarioExecutorRepository = testScenarioExecutorRepository;
    }

    public List<TestScenarioExecutor> getAllTestScenarioExecutors() {
        return testScenarioExecutorRepository.getAllTestScenarioExecutors();
    }

    public TestReport executeAllTests() {
        TestReport testReport = new TestReport();
        List<TestScenarioExecutor> executors = testScenarioExecutorRepository.getAllTestScenarioExecutors();
        executors.forEach(e -> testReport.addTestResult(executeTest(e)));
        return testReport;
    }

    private TestResult executeTest(TestScenarioExecutor testScenarioExecutor) {
        return testScenarioExecutor.buildTestExecutor().execute();
    }

    public TestScenarioExecutor getTestScenarioExecutorsByNumber(long scenarioNumber) {
        return testScenarioExecutorRepository.fetchTestScenarioExecutor(scenarioNumber);
    }

    public TestReport executeTestScenario(long number) {
        TestReport testReport = new TestReport();
        TestScenarioExecutor testScenarioExecutor =
                testScenarioExecutorRepository.fetchTestScenarioExecutor(number);
        testReport.addTestResult(executeTest(testScenarioExecutor));
        return testReport;
    }
}
