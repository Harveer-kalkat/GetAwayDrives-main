package com.example.getawaydrives;

import com.example.getawaydrives.entities.Vehicle;
import com.example.getawaydrives.repositories.VehicleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Date;

@SpringBootApplication
public class GetAwayDrivesApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetAwayDrivesApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(VehicleRepository vehicleRepository) {
//        return args -> {
//            vehicleRepository.save(new Vehicle(null, "Ford Mustang", "Convertible", "Available", "Surrey", "Manual", "10 days",
//                    "2 days", "189731246021-21e", "11 L/100 KM", "2018", "/images/img1.jpg", "$320/Day", "Ford", "NW2 22L", 1
//            ));
//
//            vehicleRepository.findAll().forEach(p -> {
//                System.out.println(p.getName());
//            });
//        };
//
//    }

}
