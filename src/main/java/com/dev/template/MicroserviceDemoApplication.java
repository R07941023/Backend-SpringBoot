package com.dev.template;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class MicroserviceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceDemoApplication.class, args);
	}

}
