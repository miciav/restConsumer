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
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
      if (testPost()) {
		//there is in the db a pet named bobby
    	  	if (testGet()) {
			if(testPUT()){
				testDELETE();
			};
			}
      }
        
    }

	private static boolean testDELETE() {
		RestTemplate rt = new  RestTemplate(); 
		rt.delete(URL+"/"+"Bobby");
		Pet bobby = rt.getForObject(URL+"/"+"Bobby", Pet.class);
		if (bobby == null) {
			System.out.println("Success DELETE ");
			return true;
		}
		else{
			System.out.println("Fail DELETE ");
			return false;
		}
		
		
	}

	private static boolean testGet() {
		RestTemplate rt = new  RestTemplate(); 
		Pet bobby = rt.getForObject(URL+"/"+"Bobby", Pet.class);
		
		if (bobby == null) {
			System.out.println("Fail GET pet null");
			return false;
		}
		if (bobby.getNome().compareTo("Bobby")==0 && 
			bobby.getDataNascita().compareTo(new Date("10/01/2013"))==0){
			System.out.println("Success GET");
			return true;
		}
		else return false;
		
	}
	
	private static boolean testPUT() {
		RestTemplate rt = new  RestTemplate(); 
		Pet bobby = new Pet();
		bobby.setNome("Bobby");
		bobby.setDataNascita(new Date("11/01/2013"));
				rt.put(URL+"/"+"Bobby", bobby);
		Pet newPet =rt.getForObject(URL+"/"+"Bobby", Pet.class);
		
		if (newPet == null) {
			System.out.println("Fail PUT pet null");
			return false;
		}
		if (bobby.getNome().compareTo(newPet.getNome())==0 && 
			bobby.getDataNascita().compareTo(newPet.getDataNascita())==0){
			System.out.println("Success PUT");
			return true;
		}
		else return false;
		
		
		
	}

	private static boolean testPost() {
		RestTemplate rt = new  RestTemplate(); 
		  //first thing create a pet. 
		  Pet p = new Pet();
		  p.setNome("Bobby");
		  p.setDataNascita(new Date("10/01/2013"));
		  Pet responsePet = rt.postForObject(URL, p, Pet.class);  
		  
		  if (responsePet != null) {
			  	if (    (   p.getNome().compareTo(responsePet.getNome())==0) & 
			  			(p.getDataNascita().compareTo(responsePet.getDataNascita())==0)) {
			  		System.out.println("Success: POST");
			  		return true;}
			  		else{
			  			System.out.println("Fail POST Second Check: "+responsePet.getNome()+" "+responsePet.getDataNascita());
			  			return false;
			  		}
		  }
		  else{
			  System.out.println("Fail: POST");
			  return true;
		  }
	}
}
