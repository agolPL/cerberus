package pl.agol.cerberus.example;

import pl.agol.cerberus.core.ScriptExecutor;
import pl.agol.cerberus.core.ScriptExecutorFactory;

public class SimpleScriptExecutorFactory implements ScriptExecutorFactory {

    @Override
    public ScriptExecutor buildScriptExecutor() {
        return new PrintTextScriptExecutor();
    }
}
