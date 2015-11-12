package pl.agol.cerberus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class TestScenarioExecutorController {

    private final TestScenarioExecutorService testScenarioExecutorService;

    @Autowired
    public TestScenarioExecutorController(TestScenarioExecutorService testScenarioExecutorService) {
        this.testScenarioExecutorService = testScenarioExecutorService;
    }

    @RequestMapping(value = "/api/test-scenario-executors", method = GET)
    public List<TestScenarioExecutor> getAllTestScenarioExecutors() {
        return testScenarioExecutorService.getAllTestScenarioExecutors();
    }

    @RequestMapping(value = "/api/test-scenario-executors/execute", method = GET)
    public TestReport executeAllTestScenario() {
        return testScenarioExecutorService.executeAllTests();
    }

    @RequestMapping(value = "/api/test-scenario-executors/{number}", method = GET)
    public TestScenarioExecutor getTestScenarioExecutorsByNumber(
            @PathVariable("number") long number) {

        return testScenarioExecutorService.getTestScenarioExecutorsByNumber(number);
    }

    @RequestMapping(value = "/api/test-scenario-executors/{number}/execute", method = GET)
    public TestReport executeTestScenario(
            @PathVariable("number") long number) {

        return testScenarioExecutorService.executeTestScenario(number);
    }
}
