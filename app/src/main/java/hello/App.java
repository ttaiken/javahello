/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package hello;

import java.util.Arrays;

//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;

// ignore database connection
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//@SpringBootApplication( exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@SpringBootApplication( exclude={DataSourceAutoConfiguration.class})
public class App {

   public String getGreeting() {
        return "Hello World!";
    }
/*
    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
*/

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(App.class, args);
        System.out.println("Let's inspect the beans provided by Spring Boot:");
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }       

    }

}
