/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp;

import com.evnit.ttpm.AuthApp.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = { AuthAppApplication.class, Jsr310JpaConverters.class })
@EnableConfigurationProperties(FileStorageProperties.class)
public class AuthAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(AuthAppApplication.class, args);
	}
//    @Scheduled(fixedDelay = 5000)
//    public void printCurrentDate()
//    {
//        System.out.println("RC time service :" + new GregorianCalendar().getTime());
//    }
}
