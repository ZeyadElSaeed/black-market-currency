package com.zeyad.blackmarketcurrency;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Properties;

@SpringBootApplication
@EnableScheduling
public class BlackMarketCurrencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackMarketCurrencyApplication.class, args);
	}

}

// nbds rvmv nmtl xamk
// xsmtpsib-a5e6d525693eaa5dbd7033d9c334794b0f9afd9a704717039fa2ff25c53825b1-XSrk48KjycpFDwvz