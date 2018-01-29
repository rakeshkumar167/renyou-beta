package com.renyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.renyou.storage.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class RenyouApplication {

	public static void main(String[] args) {
		SpringApplication.run(RenyouApplication.class, args);
	}
}
