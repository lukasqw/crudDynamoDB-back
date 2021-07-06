package com.crud.dynamodb.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crud.dynamodb.entity.Book;
import com.crud.dynamodb.entity.BookDto;
import com.crud.dynamodb.service.BookService;

@RestController
@RequestMapping("/api")
public class BookController {
	
	@Autowired
	BookService service;
	
	@GetMapping("/books")
	@ResponseStatus(HttpStatus.OK)
	public List<Book> getBooks(){
		return service.getBooks();
	}
	
	@GetMapping("/books/{title}")
	@ResponseStatus(HttpStatus.OK)
	public List<Book> getBooksByTitle(@PathVariable("title") final String title){
		return service.getBooksByTitle(title);
	}
	
	@GetMapping("/book/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Book getBookById(@PathVariable("id") final String id) {
		return service.getBookById(id);
	}
	
	@PostMapping("/book")
	@ResponseStatus(HttpStatus.CREATED)
	public Book save(@Valid @RequestBody final BookDto dto) {
		return service.save(dto);
	}
	
	@PutMapping("/book/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Book update(@PathVariable("id") final String id,@Valid @RequestBody final BookDto dto) {
		return service.update(id, dto);
	}
	
	@DeleteMapping("/book/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") final String id) {
		service.delete(id);
	}
	
	
	
	
	
	
	
	
}
