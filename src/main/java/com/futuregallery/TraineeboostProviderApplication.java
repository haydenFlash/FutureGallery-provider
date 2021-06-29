package com.futuregallery;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
@MapperScan(basePackages = "com.futuregallery.mapper")
public class TraineeboostProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraineeboostProviderApplication.class, args);
	}

}
