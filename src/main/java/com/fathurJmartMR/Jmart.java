package com.fathurJmartMR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.fathurJmartMR.dbjson.JsonDBEngine;

/**
 * Main Program untuk backed Jmart
 *
 * @author Fathurrahman Irwansa
 * @version 5 Desember 2021
 */

@SpringBootApplication
class Jmart {
    /**
     * Main program for Jmart
     * @param args default params for main program
     */
    public static void main(String[] args) {
        JsonDBEngine.Run(Jmart.class);
        SpringApplication.run(Jmart.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));
    }
}
