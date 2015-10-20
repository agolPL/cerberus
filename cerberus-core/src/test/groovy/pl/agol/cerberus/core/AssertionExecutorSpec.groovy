package pl.agol.cerberus.core

import spock.lang.Specification

class AssertionExecutorSpec extends Specification {

    AssertionExecutor assertionExecutor

    def "should check if expectation is met for a simple object"() {

        given:
            SimpleObject simpleObject = new SimpleObject(name: "andi")
            assertionExecutor = new AssertionExecutor(simpleObject)

        and:
            List<Expectation> expectations = [new Expectation("name", "andi")]

        expect:
            assertionExecutor.checkWithExpectations(expectations)
    }

    class SimpleObject {
        private String name;

        String getName() {
            return name
        }
    }

    def "should return false if expectation is not met"() {

        given:
            SimpleObject simpleObject = new SimpleObject(name: "andi")
            assertionExecutor = new AssertionExecutor(simpleObject)

        and:
            List<Expectation> expectations = [new Expectation("name", "adzia")]

        expect:
            !assertionExecutor.checkWithExpectations(expectations)
    }

    def "should check if expectations are met for a complex object"() {

        given:
            SimpleObject simpleObject = new SimpleObject(name: "andi")
            ComplexObject complexObject = new ComplexObject(someValue: "value", simpleObject: simpleObject)
            assertionExecutor = new AssertionExecutor(complexObject)

        and:
            List<Expectation> expectations = [new Expectation("someValue", "value"),
                                              new Expectation("simpleObject/name", "andi")]

        expect:
            assertionExecutor.checkWithExpectations(expectations)
    }

    class ComplexObject {

        private String someValue
        private SimpleObject simpleObject

        SimpleObject getSimpleObject() {
            return simpleObject
        }

        String getSomeValue() {
            return someValue
        }
    }

    def "should check if expectations are met for a object with collection"() {

        given:
            SimpleObject firstSimpleObject = new SimpleObject(name: "andi")
            SimpleObject secondSimpleObject = new SimpleObject(name: "adzia")

            ObjectWithCollection objectWithCollection =
                    new ObjectWithCollection(simpleObjects: [firstSimpleObject, secondSimpleObject])
            assertionExecutor = new AssertionExecutor(objectWithCollection)

        and:
            List<Expectation> expectations = [new Expectation("simpleObjects[1]/name", "andi"),
                                              new Expectation("simpleObjects[2]/name", "adzia")]

        expect:
            assertionExecutor.checkWithExpectations(expectations)
    }

    class ObjectWithCollection {

        private List<SimpleObject> simpleObjects

        List<SimpleObject> getSimpleObjects() {
            return simpleObjects
        }
    }
}
