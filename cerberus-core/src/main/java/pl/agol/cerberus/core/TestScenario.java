package pl.agol.cerberus.core;

import java.util.List;

public class TestScenario {

    private long number;
    private String name;
    private String description;
    private boolean enable;
    private String setupScript;
    private String scenarioScript;
    private String cleanupScript;
    private List<Expectation> exceptions;

    public long getNumber() {
        return number;
    }

    public TestScenario setNumber(long number) {
        this.number = number;
        return this;
    }

    public String getName() {
        return name;
    }

    public TestScenario setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TestScenario setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isEnable() {
        return enable;
    }

    public TestScenario setEnable(boolean enable) {
        this.enable = enable;
        return this;
    }

    public String getSetupScript() {
        return setupScript;
    }

    public TestScenario setSetupScript(String setupScript) {
        this.setupScript = setupScript;
        return this;
    }

    public String getScenarioScript() {
        return scenarioScript;
    }

    public TestScenario setScenarioScript(String scenarioScript) {
        this.scenarioScript = scenarioScript;
        return this;
    }

    public String getCleanupScript() {
        return cleanupScript;
    }

    public TestScenario setCleanupScript(String cleanupScript) {
        this.cleanupScript = cleanupScript;
        return this;
    }

    public List<Expectation> getExceptions() {
        return exceptions;
    }

    public TestScenario setExceptions(List<Expectation> exceptions) {
        this.exceptions = exceptions;
        return this;
    }
}
