package com.petesparkingmgt;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class PetesParkingMgtApplication {


	public static void main(String[] args) {

		SpringApplication.run(PetesParkingMgtApplication.class, args);
	}


}
