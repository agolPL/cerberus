package pl.agol.cerberus.core;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.JXPathNotFoundException;

import java.util.List;
import java.util.Objects;

public class AssertionExecutor {

    private final JXPathContext jxPathContext;

    public AssertionExecutor(Object actualValue) {
        jxPathContext = JXPathContext.newContext(actualValue);
    }

    public boolean checkWithExpectations(List<Expectation> expectations) {
        boolean result = true;
        for (Expectation expectation : expectations) {
            if (!compareValues(expectation)) {
                result = false;
            }
        }
        return result;
    }

    private boolean compareValues(Expectation expectation) {
        try {
            Object expectedValue = jxPathContext.getValue(expectation.getPath());
            return Objects.equals(expectedValue, expectation.getValue());
        } catch (JXPathNotFoundException e) {
            return false;
        }
    }
}
