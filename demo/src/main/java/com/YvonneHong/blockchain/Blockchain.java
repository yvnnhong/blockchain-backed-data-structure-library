package com.YvonneHong.blockchain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Blockchain {

	//ENTRY POINT OF MY APPLICATION
	//optional -- only if I need an entry point 
	//a pure library doesn't need this 
	//for now, keep this just in case 
	//i need this if i wanna add a web service 

	public static void main(String[] args) {
		SpringApplication.run(Blockchain.class, args);
	}

}
