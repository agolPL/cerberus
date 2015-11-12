package pl.agol.cerberus;

import pl.agol.cerberus.core.ScriptExecutor;
import pl.agol.cerberus.core.ScriptExecutorFactory;
import pl.agol.cerberus.core.TestExecutor;
import pl.agol.cerberus.core.TestScenario;

public class TestScenarioExecutor {

    private TestScenario testScenario;
    private Class<ScriptExecutorFactory> setupScriptExecutorFactoryClass;
    private Class<ScriptExecutorFactory> scenarioScriptExecutorFactoryClass;
    private Class<ScriptExecutorFactory> cleanupScriptExecutorFactoryClass;

    public TestExecutor buildTestExecutor() {
        return new TestExecutor.TestExecutorBuilder()
                .testScenario(testScenario)
                .setupScriptExecutor(getScriptExecutor(setupScriptExecutorFactoryClass))
                .scenarioExecutor(getScriptExecutor(scenarioScriptExecutorFactoryClass))
                .cleanupScriptExecutor(getScriptExecutor(cleanupScriptExecutorFactoryClass))
                .build();
    }

    private ScriptExecutor getScriptExecutor(Class<ScriptExecutorFactory> scenarioScriptExecutorFactoryClass) {
        try {
            ScriptExecutorFactory scriptExecutorFactory = scenarioScriptExecutorFactoryClass.newInstance();
            return scriptExecutorFactory.buildScriptExecutor();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public TestScenarioExecutor setTestScenario(TestScenario testScenario) {
        this.testScenario = testScenario;
        return this;
    }

    public TestScenarioExecutor setSetupScriptExecutorFactoryClass(
            Class<ScriptExecutorFactory> setupScriptExecutorFactoryClass) {
        this.setupScriptExecutorFactoryClass = setupScriptExecutorFactoryClass;
        return this;
    }

    public TestScenarioExecutor setScenarioScriptExecutorFactoryClass(
            Class<ScriptExecutorFactory> scenarioScriptExecutorFactoryClass) {
        this.scenarioScriptExecutorFactoryClass = scenarioScriptExecutorFactoryClass;
        return this;
    }

    public TestScenarioExecutor setCleanupScriptExecutorFactoryClass(
            Class<ScriptExecutorFactory> cleanupScriptExecutorFactoryClass) {
        this.cleanupScriptExecutorFactoryClass = cleanupScriptExecutorFactoryClass;
        return this;
    }

    public TestScenario getTestScenario() {
        return testScenario;
    }

    public Class<ScriptExecutorFactory> getSetupScriptExecutorFactoryClass() {
        return setupScriptExecutorFactoryClass;
    }

    public Class<ScriptExecutorFactory> getScenarioScriptExecutorFactoryClass() {
        return scenarioScriptExecutorFactoryClass;
    }

    public Class<ScriptExecutorFactory> getCleanupScriptExecutorFactoryClass() {
        return cleanupScriptExecutorFactoryClass;
    }
}
