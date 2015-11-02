package pl.agol.cerberus

import pl.agol.cerberus.core.ScriptExecutor
import pl.agol.cerberus.core.ScriptExecutorFactory
import pl.agol.cerberus.core.ScriptTranslator
import pl.agol.cerberus.core.TestExecutor
import pl.agol.cerberus.core.TestScenario
import spock.lang.Specification

class TestScenarioExecutorTest extends Specification {

    def "should build Test Scenario Executor"() {
        given:
            TestScenarioExecutor testScenarioExecutor = new TestScenarioExecutor()
                    .setTestScenario(new TestScenario())
                    .setSetupScriptExecutorFactoryClass(SetupScriptExecutorFactory.class)
                    .setScenarioScriptExecutorFactoryClass(ScriptScriptExecutorFactory.class)
                    .setCleanupScriptExecutorFactoryClass(CleanupScriptExecutorFactory.class)

        when:
            TestExecutor testExecutor = testScenarioExecutor.buildTestExecutor()

        then:
            with(testExecutor) {
                testScenario != null
                setupScriptExecutor.name == "setup"
                scenarioExecutor.name == "script"
                cleanupScriptExecutor.name == "cleanup"
            }
    }

    public static class SetupScriptExecutorFactory implements ScriptExecutorFactory {

        @Override
        ScriptExecutor buildScriptExecutor() {
            return new AnyScriptExecutor("setup")
        }
    }

    public static class ScriptScriptExecutorFactory implements ScriptExecutorFactory {

        @Override
        ScriptExecutor buildScriptExecutor() {
            return new AnyScriptExecutor("script")
        }
    }

    public static class CleanupScriptExecutorFactory implements ScriptExecutorFactory {

        @Override
        ScriptExecutor buildScriptExecutor() {
            return new AnyScriptExecutor("cleanup")
        }
    }

    public static class AnyScriptExecutor implements ScriptExecutor {

        private final String name

        public AnyScriptExecutor(String name) {
            this.name = name
        }

        @Override
        void setTranslator(ScriptTranslator scriptTranslator) {
        }

        @Override
        Object execute(String script) {
            return null
        }

        String getName() {
            return name
        }
    }
}
