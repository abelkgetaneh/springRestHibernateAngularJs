package com.abelkbde.springmvccrudrestservice.service;

import java.util.List;

import com.abelkbde.springmvccrudrestservice.model.Book;


public interface BookService {
	 
    void saveBook(Book book);
 
    List<Book> findAllBooks();
 
    void deleteBookById(Integer id); 
 
    void updateBook(Book Book);

	void deleteAllBooks();

	Boolean isBookExist(Book book);

	Book findBookById(Integer id);
}
