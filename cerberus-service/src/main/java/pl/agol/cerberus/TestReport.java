package pl.agol.cerberus;

import pl.agol.cerberus.core.TestResult;

import java.util.ArrayList;
import java.util.List;

public class TestReport {

    private final List<TestResult> testResults = new ArrayList<>();

    public void addTestResult(TestResult testResult) {
        testResults.add(testResult);
    }

    public List<TestResult> getTestResults() {
        return testResults;
    }
}
