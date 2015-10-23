package pl.agol.cerberus.core;

import java.util.List;

public class TestScenario {

    private String name;
    private String description;
    private boolean enable;
    private String setupScript;
    private String scenarioScript;
    private String cleanupScript;
    private List<Expectation> exceptions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getSetupScript() {
        return setupScript;
    }

    public void setSetupScript(String setupScript) {
        this.setupScript = setupScript;
    }

    public String getScenarioScript() {
        return scenarioScript;
    }

    public void setScenarioScript(String scenarioScript) {
        this.scenarioScript = scenarioScript;
    }

    public String getCleanupScript() {
        return cleanupScript;
    }

    public void setCleanupScript(String cleanupScript) {
        this.cleanupScript = cleanupScript;
    }

    public List<Expectation> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<Expectation> exceptions) {
        this.exceptions = exceptions;
    }
}
