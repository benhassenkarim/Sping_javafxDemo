package com.example.javafx;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = "classpath:application.properties")
@SpringBootApplication
public class fxApplication {
    public static void main(String[] args) {
        //	SpringApplication.run(JavafxApplication.class, args);
        Application.launch(JavafxApplication.class,args);



    }


}
