package com.abelkbde.springmvccrudrestservice.dao;

import java.util.List;

import com.abelkbde.springmvccrudrestservice.model.Book;


public interface BookDao {

	void saveBook(Book book);
	
	List<Book> findAllBooks();	
	
	Book findBookById(Integer id);
	
	void updateBook(Book book);

	void deleteAllBooks();

	Boolean isBookExist(Book book);

	void deleteBookById(Integer id);
}
