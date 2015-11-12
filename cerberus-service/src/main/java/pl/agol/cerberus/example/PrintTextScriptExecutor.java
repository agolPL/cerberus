package pl.agol.cerberus.example;

import pl.agol.cerberus.core.ScriptExecutor;
import pl.agol.cerberus.core.ScriptTranslator;


public class PrintTextScriptExecutor implements ScriptExecutor {

    @Override
    public void setTranslator(ScriptTranslator scriptTranslator) {
    }

    @Override
    public Object execute(String script) {
        System.out.println("Executing test script: " + script);
        return new Person("andi", "golawski");
    }
}
