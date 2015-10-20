package pl.agol.cerberus.executor

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import pl.agol.cerberus.core.ScriptTranslator
import spock.lang.Specification

import javax.sql.DataSource

class SqlScriptExecutorSpec extends Specification {

    SqlScriptExecutor sqlScriptExecutor
    DataSource dataSource

    def setup() {
        dataSource = buildDataSource()
        sqlScriptExecutor = new SqlScriptExecutor(dataSource)
        sqlScriptExecutor.setTranslator(new DummyScriptTranslator())
    }

    def "should create table test and insert a given value in it"() {

        given:
            String sqlScript = """  CREATE TABLE test(name varchar);
                                    INSERT INTO test(name) VALUES ('andi');
                                """
        when:
            sqlScriptExecutor.execute(sqlScript)

        then:
            getNameValueFromTestTable() == "andi"
    }

    private String getNameValueFromTestTable() {
        new JdbcTemplate(dataSource)
                .queryForObject("SELECT name FROM test", String.class);
    }

    def "should rise a CouldNotExecuteScript exception if script is not compatible with the SQL format"() {

        given:
            String incorrectScript = "Some dummy text"

        when:
            sqlScriptExecutor.execute(incorrectScript)

        then:
            thrown(CouldNotExecuteScript.class)
    }

    private class DummyScriptTranslator implements ScriptTranslator {

        @Override
        String translate(String script) {
            return script
        }
    }

    private DataSource buildDataSource() {
        new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }
}
