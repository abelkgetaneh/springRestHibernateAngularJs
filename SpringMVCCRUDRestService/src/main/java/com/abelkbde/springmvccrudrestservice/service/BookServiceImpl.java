package com.abelkbde.springmvccrudrestservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abelkbde.springmvccrudrestservice.dao.BookDao;
import com.abelkbde.springmvccrudrestservice.model.Book;



@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService{

	@Autowired
	private BookDao dao;
	
	@Override
	public void saveBook(Book book) {
		dao.saveBook(book);
		
	}
	
	@Override
	public List<Book> findAllBooks() {
		return dao.findAllBooks();
	}

	@Override
	public void deleteBookById(Integer id) {
		dao.deleteBookById(id);
		
	}

	@Override
	public Book findBookById(Integer id) {
		return dao.findBookById(id);
	}

	@Override
	public void updateBook(Book book) {
		dao.updateBook(book);
		
	}

	@Override
	public void deleteAllBooks() {
		dao.deleteAllBooks();
		
	}

	@Override
	public Boolean isBookExist(Book book) {
		return dao.isBookExist(book);
	}

}
