package com.itdreamworks.newdatacache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.itdreamworks.newdatacache")
@EnableCaching
public class NewdatacacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewdatacacheApplication.class, args);
	}
}
