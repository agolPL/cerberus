package pl.agol.cerberus.core;

public class TestExecutor {

    private final TestScenario testScenario;
    private final ScriptExecutor setupScriptExecutor;
    private final ScriptExecutor testExecutor;
    private final ScriptExecutor cleanupScriptExecutor;

    public TestExecutor(TestScenario testScenario,
                        ScriptExecutor setupScriptExecutor,
                        ScriptExecutor testExecutor,
                        ScriptExecutor cleanupScriptExecutor) {

        this.testScenario = testScenario;
        this.setupScriptExecutor = setupScriptExecutor;
        this.testExecutor = testExecutor;
        this.cleanupScriptExecutor = cleanupScriptExecutor;
    }

    public boolean execute() {

        setupScriptExecutor.execute(testScenario.getSetupScript());
        Object testResult = testExecutor.execute(testScenario.getTestScript());

        boolean assertionResult = new AssertionExecutor(testResult)
                .checkWithExpectations(testScenario.getExceptions());

        cleanupScriptExecutor.execute(testScenario.getCleanupScript());
        return assertionResult;
    }

    public static class TestExecutorBuilder {

        private TestScenario testScenario;
        private ScriptExecutor setupScriptExecutor;
        private ScriptExecutor testExecutor;
        private ScriptExecutor cleanupScriptExecutor;

        public void testScenario(TestScenario testScenario) {
            this.testScenario = testScenario;
        }

        public void setupScriptExecutor(ScriptExecutor setupScriptExecutor) {
            this.setupScriptExecutor = setupScriptExecutor;
        }

        public void testExecutor(ScriptExecutor testExecutor) {
            this.testExecutor = testExecutor;
        }


        public void cleanupScriptExecutor(ScriptExecutor cleanupScriptExecutor) {
            this.cleanupScriptExecutor = cleanupScriptExecutor;
        }

        public TestExecutor build() {
            return new TestExecutor(
                    testScenario, setupScriptExecutor, testExecutor, cleanupScriptExecutor);
        }
    }
}
