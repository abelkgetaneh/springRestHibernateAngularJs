package com.abelkbde.springmvccrudrestservice.test;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import com.abelkbde.springmvccrudrestservice.model.Book;

public class SpringRestTestClient {

	private static final Logger LOGGER = Logger.getLogger(SpringRestTestClient.class);
	
	public static final String REST_SERVICE_URI = "http://localhost:8088/SpringMVCCRUDRestService";
	
	@SuppressWarnings("unchecked")
	    private static void listAllBooks(){
		LOGGER.info("Testing listAllbooks API-----------");
	         
	        RestTemplate restTemplate = new RestTemplate();
	        List<LinkedHashMap<String, Object>> booksMap = restTemplate.getForObject(REST_SERVICE_URI+"/book/", List.class);
	         
	        if(booksMap!=null){
	            for(LinkedHashMap<String, Object> map : booksMap){
	                System.out.println("book : id="+map.get("id")+", Title="+map.get("title")+", Author="+map.get("author"));
	            }
	        }else{
	        	LOGGER.info("No book exists----------");
	        }
	    }
	
	/* GET */
    private static void getBook(){
    	LOGGER.info("Testing getbook API----------");
        RestTemplate restTemplate = new RestTemplate();
        Book book = restTemplate.getForObject(REST_SERVICE_URI+"/book/2", Book.class);
        LOGGER.info(book);
    }
    
    /*POST*/
    private static void createBook() {
    	LOGGER.info("Testing create book API-------------");
    	RestTemplate restTemplate = new RestTemplate();
    	Book book = new Book("Don Quixote", "Miguel de Cervantes");
    	URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/book/", book, Book.class);
    	LOGGER.info("Location : " + uri.toASCIIString());    	
    }
    
    /*PUT*/
    private static void updateBook() {
    	LOGGER.info("Testing update book API----------------------");
    	RestTemplate restTemplate = new RestTemplate();
    	Book book = new Book("War and Peace", "Leo Tolstoy");
    	restTemplate.put(REST_SERVICE_URI+"/book/4", book);
    	System.out.println(book);
    }
    
    /*DELETE*/
    private static void deletebook() {
    	LOGGER.info("Testing delete book API-----------------------");
    	RestTemplate restTemplate = new RestTemplate();
    	restTemplate.delete(REST_SERVICE_URI+"/book/19");
    }
    
    /*DELETE*/
    private static void deleteAllbooks() {
    	System.out.println("Testing delete book API-----------------------");
    	RestTemplate restTemplate = new RestTemplate();
    	restTemplate.delete(REST_SERVICE_URI+"/book/");
    }
    
    public static void main(String args[]) {
    	listAllBooks();
    	getBook();
    	createBook();
    	listAllBooks();
    	updateBook();
    	listAllBooks();
    	deletebook();
    	listAllBooks();
    	deleteAllbooks(); 
		listAllBooks();
		 
    }
    
    
}
