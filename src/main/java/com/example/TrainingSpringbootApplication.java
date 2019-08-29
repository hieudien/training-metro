package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class TrainingSpringbootApplication {
    public static Connection con;

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test", "root", "Hieudien");
        } catch (Exception e) {
            System.out.println(e);
        }

        SpringApplication.run(TrainingSpringbootApplication.class, args);
    }
}
