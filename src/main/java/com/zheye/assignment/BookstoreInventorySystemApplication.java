package com.zheye.assignment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.zheye.assignment.mapper")
public class BookstoreInventorySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreInventorySystemApplication.class, args);
	}

}
