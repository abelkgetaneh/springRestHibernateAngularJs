package com.abelkbde.springmvccrudrestservice.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.abelkbde.springmvccrudrestservice.model.Book;
import com.abelkbde.springmvccrudrestservice.service.BookService;

@RestController
public class BookRestController {

	private static final Logger LOGGER = Logger.getLogger(BookRestController.class);
	
	@Autowired
	private BookService bookService;
	
	//-------------------------Fetch All Users---------------------------
	@RequestMapping(value = "/book/", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> listAllBooks(){
		LOGGER.info("Fetching all users");
		List<Book> books = bookService.findAllBooks();
		if(books.isEmpty()) {
			return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}
	
	//----------------------Fetch Single Book--------------------------------------------------------------------
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> findBook(@PathVariable("id") Integer id){
		LOGGER.info("Fetching book with id " + id);
		Book book = bookService.findBookById(id);
		if(book == null) {
			System.out.println("Book with id " + id + " not found");
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	//--------------------------Create a Book--------------------------------------------------
	@RequestMapping(value = "/book/", method = RequestMethod.POST)
	public ResponseEntity<Void> createBook(@RequestBody Book book, UriComponentsBuilder ucBuilder){
		LOGGER.info("Creating book " + book.getId());
		
		if(bookService.isBookExist(book)) {
			System.out.println("A book with title " + book.getId() + " already exists.");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		bookService.saveBook(book);;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/book/{id}").buildAndExpand(book.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	//----------------------Update a Book---------------------------------------------------------
	@RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Book> updateBook(@PathVariable("id") Integer id, @RequestBody Book book){
		LOGGER.info("Updating book " + id);
		
		Book currentBook = bookService.findBookById(id);
		
		if(currentBook==null) {
			LOGGER.info("Book with title " + id + " not found");
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
		
		currentBook.setTitle(book.getTitle());;
		currentBook.setAuthor(book.getAuthor());		

		
		bookService.updateBook(currentBook);
		return new ResponseEntity<Book>(currentBook, HttpStatus.OK);
	}
	
	//---------------------Delete a Book------------------------------------------------------
	@RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Book> deleteBook(@PathVariable("id") Integer id){
		LOGGER.info("Fetching and deleting a book with title " + id);
		
		Book book = bookService.findBookById(id);
		if(book == null) {
			LOGGER.info("Unable to delete book by id " + id + " not found.");
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
		bookService.deleteBookById(id);
		return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
	}
	
	//--------------------Delete All Books----------------------------------------------------
	@RequestMapping(value = "/book/", method = RequestMethod.DELETE)
	public ResponseEntity<Book> deleteAllBooks(){
		LOGGER.info("Deleting all books");
		
		bookService.deleteAllBooks();
		return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
	}
}
