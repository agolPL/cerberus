package pl.agol.cerberus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CerberusService {

    private static final String APPLICATION_PID_FILE_NAME = "cerberus-service.pid";

    public static void main(String... args) {
        SpringApplication cerberusApplication = new SpringApplication(CerberusService.class);
        cerberusApplication.addListeners(new ApplicationPidFileWriter(APPLICATION_PID_FILE_NAME));
        cerberusApplication.run(args);
    }
}
