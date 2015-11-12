package pl.agol.cerberus;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

@Service
public class ClassLoaderService {

    private static final String EXTERNAL_LIB_PATH_SYSTEM_PROPERTY_NAME = "extLib";

    private URLClassLoader scenarioExecutorFactoryClassLoader;

    @PostConstruct
    private void createTestScenarioExecutorFactoryClassLoader() throws MalformedURLException {
        String externalLibPath = System.getProperty(EXTERNAL_LIB_PATH_SYSTEM_PROPERTY_NAME);
        URL[] classLoaderUrls = new URL[]{new URL("file:" + externalLibPath)};
        scenarioExecutorFactoryClassLoader = new URLClassLoader(classLoaderUrls);
    }

    public <T> Class<T> loadClass(String className, Class<T> type) throws ClassNotFoundException {
        return (Class<T>) scenarioExecutorFactoryClassLoader.loadClass(className);
    }
}
