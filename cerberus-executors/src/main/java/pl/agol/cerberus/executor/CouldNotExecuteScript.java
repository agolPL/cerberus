package pl.agol.cerberus.executor;

public class CouldNotExecuteScript extends RuntimeException {

    public CouldNotExecuteScript(String script, Throwable throwable) {
        super(createMessage(script), throwable);
    }

    private static String createMessage(String script) {
        return String.format("Could not execute script:\n%s", script);
    }
}
