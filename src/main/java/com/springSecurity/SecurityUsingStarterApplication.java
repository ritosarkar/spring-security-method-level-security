package com.springSecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


/*
* In case we hadn't follow the traditional project module to
* write our 1) entity class and 2)JpaRepository class
* In such cases we need to notify same using
* a) @EntityScan("package.name.ofEntity")
* b) @EnableJpaRepositories("package.name.ofRepository")
* */

/*
@EnableWebSecurity
This annotation is optional (inside spring boot environment)
in case of enabling the spring security; spring is smart enough to enable the annotations
by looking into the pom.xml configuration.
But in case of regular spring application mentioning this annotation is mandatory.
*/

@SpringBootApplication
@EnableWebSecurity
public class SecurityUsingStarterApplication {
   
	public static void main(String[] args) {
		SpringApplication.run(SecurityUsingStarterApplication.class, args);
	}

}
