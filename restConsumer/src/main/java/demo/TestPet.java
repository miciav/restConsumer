package demo;

import it.springlesson4.domain.Pet;

import java.util.Date;

import org.springframework.web.client.RestTemplate;

public class TestPet {

	public static void testPet(String URL) {
		if (testPost(URL)) {
			//there is in the db a pet named bobby
			  	if (testGet(URL)) {
				if(testPUT(URL)){
					testDELETE(URL);
				};
				}
		  }
	}

	private static boolean testDELETE(String URL) {
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

	private static boolean testGet(String URL) {
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
	
	private static boolean testPUT(String URL) {
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

	private static boolean testPost(String URL) {
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
