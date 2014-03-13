package demo;

import it.springlesson4.domain.Proprietario;

import org.springframework.web.client.RestTemplate;

public class TestProprietario {

	public static void testProprietario(String URL){
		if (testPost(URL)){
			//there is in the db a pet named bobby
			  	if (testGet(URL+"/1")) {
				if(testPUT(URL+"/1")){
					testDELETE(URL+"/1");
				};
				}
		  }		
	}

	private static boolean testDELETE(String URL) {
		RestTemplate rt = new  RestTemplate(); 
		rt.delete(URL);
		Proprietario p = rt.getForObject(URL, Proprietario.class);
		if (p == null) {
			System.out.println("Success DELETE ");
			return true;
		}
		else{
			System.out.println("Fail DELETE ");
			return false;
		}
	}
	private static boolean testGet(String propPos) {
		RestTemplate rt = new  RestTemplate(); 
		Proprietario mic = rt.getForObject(propPos, Proprietario.class);
		
		if (mic == null) {
			System.out.println("Fail GET proprietario null");
			return false;
		}
		if (mic.getNome().compareTo("Michele")==0){
			System.out.println("Success GET");
			return true;
		}
		else return false;
	}
	private static boolean testPost(String URL) {
		String urlResp = "";
		RestTemplate rt = new  RestTemplate(); 
		  //first thing create a pet. 
		  Proprietario p = new Proprietario();
		  p.setNome("Michele");
		  p.setCognome("DiStefano");
		  p.setCitta("Milano");
		  p.setIndirizzo("via Porpora, 15");
		  p.setTelefono("02545592");
		  
		  Proprietario prop =rt.postForObject(URL, p, Proprietario.class);
		  if (prop.getNome().compareTo("Michele")==0) {
			  System.out.println("POST OK");
			  return true;
			
		  }
		  System.out.println("POST FAIL");
		  return false;
		  
	}
	private static boolean testPUT(String propPos) {
		RestTemplate rt = new  RestTemplate(); 
		Proprietario p = rt.getForObject(propPos, Proprietario.class);
		p.setNome("Antonio");
		rt.put(propPos, p);
		Proprietario newProp =rt.getForObject(propPos, Proprietario.class);
		
		if (newProp == null) {
			System.out.println("Fail PUT proprietario null");
			return false;
		}
		if (p.getNome().compareTo(newProp.getNome())==0){
			System.out.println("Success PUT");
			return true;
		}
		else return false;
		
		
		
	}
	
}

