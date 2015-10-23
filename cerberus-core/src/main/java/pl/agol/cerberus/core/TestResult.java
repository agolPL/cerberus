package pl.agol.cerberus.core;

public class TestResult {

    private final String massage;
    private final Object result;

    public TestResult(String massage, Object result) {
        this.massage = massage;
        this.result = result;
    }

    public String getMassage() {
        return massage;
    }

    public Object getResult() {
        return result;
    }
}
