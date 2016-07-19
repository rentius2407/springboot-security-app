package com.ren;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ren")
public class Application {
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

/**
 * If one wants to deploy to Apache Tomcat 8, comment out the above code and remove comments below
 */

//@SpringBootApplication
//public class Application extends SpringBootServletInitializer {
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(Application.class);
//    }
//
//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(Application.class, args);
//    }
//
//}
