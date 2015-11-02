package pl.agol.cerberus.core;

public class TestExecutor {

    private static final String TEST_OK_MESSAGE = "ok";

    private final TestScenario testScenario;
    private final ScriptExecutor setupScriptExecutor;
    private final ScriptExecutor scenarioExecutor;
    private final ScriptExecutor cleanupScriptExecutor;
    private final boolean goldenMasterMode;

    public TestExecutor(TestScenario testScenario,
                        ScriptExecutor setupScriptExecutor,
                        ScriptExecutor scenarioExecutor,
                        ScriptExecutor cleanupScriptExecutor,
                        boolean goldenMasterMode) {

        this.testScenario = testScenario;
        this.setupScriptExecutor = setupScriptExecutor;
        this.scenarioExecutor = scenarioExecutor;
        this.cleanupScriptExecutor = cleanupScriptExecutor;
        this.goldenMasterMode = goldenMasterMode;
    }

    public TestResult execute() {

        setupScriptExecutor.execute(testScenario.getSetupScript());
        Object scenarioResult = scenarioExecutor.execute(testScenario.getScenarioScript());

        Object testResult = executeTest(scenarioResult);

        cleanupScriptExecutor.execute(testScenario.getCleanupScript());
        return new TestResult(TEST_OK_MESSAGE, testResult);
    }

    private Object executeTest(Object scenarioResult) {
        if (!goldenMasterMode) {
            return new AssertionExecutor(scenarioResult)
                    .checkWithExpectations(testScenario.getExceptions());
        }
        return scenarioResult;
    }

    public static class TestExecutorBuilder {

        private TestScenario testScenario;
        private ScriptExecutor setupScriptExecutor;
        private ScriptExecutor scenarioExecutor;
        private ScriptExecutor cleanupScriptExecutor;
        private boolean goldenMasterMode;

        public TestExecutorBuilder testScenario(TestScenario testScenario) {
            this.testScenario = testScenario;
            return this;
        }

        public TestExecutorBuilder setupScriptExecutor(ScriptExecutor setupScriptExecutor) {
            this.setupScriptExecutor = setupScriptExecutor;
            return this;
        }

        public TestExecutorBuilder scenarioExecutor(ScriptExecutor scenarioExecutor) {
            this.scenarioExecutor = scenarioExecutor;
            return this;
        }

        public TestExecutorBuilder cleanupScriptExecutor(ScriptExecutor cleanupScriptExecutor) {
            this.cleanupScriptExecutor = cleanupScriptExecutor;
            return this;
        }

        public TestExecutorBuilder goldenMasterMode(boolean goldenMasterMode) {
            this.goldenMasterMode = goldenMasterMode;
            return this;
        }

        public TestExecutor build() {
            return new TestExecutor(
                    testScenario, setupScriptExecutor, scenarioExecutor, cleanupScriptExecutor, goldenMasterMode);
        }
    }
}
