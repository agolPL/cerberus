package pl.agol.cerberus.executor;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import pl.agol.cerberus.core.ScriptExecutor;
import pl.agol.cerberus.core.ScriptTranslator;

import javax.sql.DataSource;

public class SqlScriptExecutor implements ScriptExecutor {

    private final DataSource dataSource;

    private ScriptTranslator scriptTranslator;

    public SqlScriptExecutor(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Object execute(String script) {
        String translatedScript = translateScript(script);
        executeSqlScript(translatedScript);
        return Void.TYPE;
    }

    private void executeSqlScript(String translatedScript) {
        try {
            ScriptUtils.executeSqlScript(dataSource.getConnection(), toResource(translatedScript));
        } catch (Exception e) {
            throw new CouldNotExecuteScript(translatedScript, e);
        }
    }

    private String translateScript(String script) {
        return scriptTranslator != null
                ? scriptTranslator.translate(script)
                : script;
    }

    private Resource toResource(String translatedScript) {
        return new ByteArrayResource(translatedScript.getBytes());
    }

    @Override
    public void setTranslator(ScriptTranslator scriptTranslator) {
        this.scriptTranslator = scriptTranslator;
    }
}
