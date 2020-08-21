package kr.co.study.eatgo.eatgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("")
public class EatgoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EatgoApplication.class, args);
	}

}
