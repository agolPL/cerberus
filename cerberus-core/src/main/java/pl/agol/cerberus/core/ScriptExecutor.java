package pl.agol.cerberus.core;

public interface ScriptExecutor {

    void setTranslator(ScriptTranslator scriptTranslator);

    Object execute(String script);
}
