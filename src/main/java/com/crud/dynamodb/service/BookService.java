package com.crud.dynamodb.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.dynamodb.entity.Book;
import com.crud.dynamodb.entity.BookDto;
import com.crud.dynamodb.exception.EntityNotFoundExeceptions;
import com.crud.dynamodb.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository repository;
	
	public Book save(final BookDto dto) {
		final Book b = createBookBuilder(dto);
		return repository.save(b);
	}
	
	// get all books
	public List<Book> getBooks() {
		final Iterable<Book> allBooks = repository.findAll();
		return StreamSupport.stream(allBooks.spliterator(), false).collect(Collectors.toList());
	}
	
	// get book by id
	public Book getBookById(final String id) {
		return repository.findById(id).orElseThrow(
				() -> new EntityNotFoundExeceptions("Id not found ["+ id +"]"));
	}
	
	// update book by id
	public Book update(final String id, final BookDto dto) {
		getBookById(id);
		final Book bookToBeUpdated = createBookBuilder(dto);
		bookToBeUpdated.setId(id);
		
		return repository.save(bookToBeUpdated);
	}
	
	// delete book by id
	public void delete(final String id) {
		getBookById(id);
		repository.deleteById(id);
	}
	
	// get all books by author
	public List<Book> getBooksByTitle(final String title) {
		return repository.findAllByTitleContaining(title);
	}
	
	//helper method
    private Book createBookBuilder(final BookDto dto) {
    	Book book = new Book();
    	book.setTitle(dto.getTitle());
    	book.setAuthor(dto.getAuthor());
    	book.setGenre(dto.getGenre());
    	book.setPublisher(dto.getPublisher());
    	book.setStars(dto.getStars());
    	book.setSynopsis(dto.getSynopsis());
    	book.setComments(dto.getComments());
    	book.setCoverUrl(dto.getCoverUrl());
    	return book;
    }
}
