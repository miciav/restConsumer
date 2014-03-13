package demo;

import java.util.Date;

import it.springlesson4.domain.Pet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

	private static final String URL = "http://localhost:8080/NegozioAnimali/pets/pet";
	private static final String URL2 ="http://localhost:8080/NegozioAnimali/p/prop";
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
    //  TestPet.testPet(URL);
      TestProprietario.testProprietario(URL2);
      
    }

	
}
