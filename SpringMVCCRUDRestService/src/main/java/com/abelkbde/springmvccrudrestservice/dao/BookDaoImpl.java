package com.abelkbde.springmvccrudrestservice.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.abelkbde.springmvccrudrestservice.model.Book;


@Repository("bookDao")
public class BookDaoImpl extends AbstractDao implements BookDao{

	@Override
	public void saveBook(Book book) {
		persist(book);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> findAllBooks() {
		Criteria criteria = getSession().createCriteria(Book.class);
		return (List<Book>)criteria.list();
	}

	@Override
	public void deleteBookById(Integer id) {
		Query query = getSession().createSQLQuery("delete from Book where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
		
	}

	@Override
	public Book findBookById(Integer id) {
		Criteria criteria = getSession().createCriteria(Book.class);
		criteria.add(Restrictions.eq("id", id));
		return (Book) criteria.uniqueResult();
	}

	@Override
	public void updateBook(Book book) {
		getSession().update(book);
		
	}
	
	@Override
	public Boolean isBookExist(Book book) {
		return (findBookById(book.getId()) != null);
	}

	@Override
	public void deleteAllBooks() {
		Query query = getSession().createSQLQuery("delete from Book");
		query.executeUpdate();
		
	}

}
