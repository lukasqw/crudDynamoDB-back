package com.crud.dynamodb.repository;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crud.dynamodb.entity.Book;

@EnableScan
@Repository
public interface BookRepository extends CrudRepository<Book, String> {
	
	List<Book> findAllByTitleContaining(String title);
	
	List<Book> findAllByGenre(String genre);
	
}	
