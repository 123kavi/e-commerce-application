package com.java.store.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class StoreApplication  {	

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

}
