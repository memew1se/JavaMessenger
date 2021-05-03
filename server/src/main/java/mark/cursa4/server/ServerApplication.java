package mark.cursa4.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Messenger server application
 */
@SpringBootApplication
public class ServerApplication {

    /**
     * Server launcher
     *
     * @param args input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}
