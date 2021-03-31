package com.example.demo.automationTests;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.example.demo.model.Account;

public class BasicTest {

	Account responseBodyPOST=null;
	private RestTemplate restTemplate;
	ResponseEntity<Account> response;
	
	@BeforeMethod
	  public void beforeMethod() {
	      System.out.println("Launch the System");
	      this.restTemplate = new RestTemplate();
	  }
	
	  @Test
	  public void addAccountTest() {
	     
	    String addURI = "http://localhost:8081/api/accounts";
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Accept", "application/json");
	    headers.add("Content-Type", "application/json");
	    String jsonBody = "{\"name\":\"zozo100\",\"phone\":\"9999999999\",\"email\":\"genericname@company.com\",\"address\":\"Generic Street 42 Earth\",\"country\":\"Arvala\",\"department\":\"T21R\"}";
	    HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
	    //RestTemplate restTemplate = new RestTemplate();
	     response =restTemplate.postForEntity(addURI, entity, Account.class);
	    responseBodyPOST = response.getBody();
	    Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
	  }
	  
	  
	  @Test(dependsOnMethods = "addAccountTest", enabled = true) 
      public void  updateAccountTest() { 
          String updateURI = "http://localhost:8081/api/accounts/"+3;
             
          String jsonBody = responseBodyPOST.toString();
           
          jsonBody = jsonBody.replace("zozo100", "update_zozo100");
           
          HttpHeaders headers = new HttpHeaders();      
          headers.add("Accept", "application/json");
          headers.add("Content-Type", "application/json");
           
          HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
          response = restTemplate.exchange(updateURI, HttpMethod.PUT, entity, Account.class);       
       
          Assert.assertTrue(response.getBody().toString().contains("update_zozo100"));
                  
      }
       
        @Test
        public void deleteEmployee()  {
            String delURI = "http://localhost:8081/api/accounts/"+3;
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<String>(headers);
             
            response = restTemplate.exchange(delURI, HttpMethod.DELETE, entity, Account.class);  
     
            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);           
             
        }
        
	  @AfterMethod
	  public void afterMethod() {
	    System.out.println("Log out from System");  
	  }
}
