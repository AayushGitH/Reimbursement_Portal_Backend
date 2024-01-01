package proj.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
* Main class containing main method to execute the application.
*/
@CrossOrigin("*")
@SpringBootApplication
public class PlugintestApplication {
  /**
  * Main method to execute whole application.
  *
  * @param args All arguments will come here first.
  */
  public static void main(final String[] args) {
    SpringApplication.run(PlugintestApplication.class, args);
  }
}
