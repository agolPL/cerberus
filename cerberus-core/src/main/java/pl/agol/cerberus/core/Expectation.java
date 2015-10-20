package pl.agol.cerberus.core;

public class Expectation {

    private final String path;
    private final Object value;

    public Expectation(String path, Object value) {
        this.path = path;
        this.value = value;
    }

    public String getPath() {
        return path;
    }

    public Object getValue() {
        return value;
    }
}
