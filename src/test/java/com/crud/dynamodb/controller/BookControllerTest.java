package com.crud.dynamodb.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.crud.dynamodb.entity.Book;
import com.crud.dynamodb.entity.BookDto;
import com.crud.dynamodb.exception.EntityNotFoundExeceptions;
import com.crud.dynamodb.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BookController.class)
public class BookControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private BookService bookService;
	
	@Test
	public void shouldReturnSuccess_WhenSavingBook() throws Exception {
		Book expectedBook = new Book();
		expectedBook.setId("123");
		expectedBook.setTitle("Test");
		expectedBook.setAuthor("Test");
		expectedBook.setGenre(new String[] {"test1", "test2"});
		expectedBook.setPublisher("Test");
		expectedBook.setStars(1);
		
		
		when(bookService.save(any(BookDto.class))).thenReturn(expectedBook);
		
		Book newBook = new Book();
		newBook.setTitle("Test");
		newBook.setAuthor("Test");
		newBook.setGenre(new String[] {"test1", "test2"});
		newBook.setPublisher("Test");
		newBook.setStars(1);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/book")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newBook)))
				.andExpect(status().isCreated())
				.andExpect(content().json(objectMapper.writeValueAsString(expectedBook)));	
	}
	
	@Test
	public void shouldReturnError_WhenSavingBookWithoutTitle() throws Exception {		
		Book newBook = new Book();
		newBook.setTitle("Test");
		newBook.setGenre(new String[] {"test1", "test2"});
		newBook.setPublisher("Test");
		newBook.setStars(1);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/book")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newBook)))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void shouldReturnError_WhenSavingBookWithoutAuthor() throws Exception {		
		Book newBook = new Book();
		newBook.setAuthor("Test");
		newBook.setGenre(new String[] {"test1", "test2"});
		newBook.setPublisher("Test");
		newBook.setStars(1);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/book")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newBook)))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void shouldReturnError_WhenSavingBookWithoutBody() throws Exception {		
		Book newBook = new Book();
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/book")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newBook)))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void shouldReturnSuccess_WhenUpdateBook() throws Exception {	
		String id = "123";
		
		Book expectedBook = new Book();
		expectedBook.setId(id);
		expectedBook.setTitle("Test2");
		expectedBook.setAuthor("Test");
		expectedBook.setGenre(new String[] {"test1", "test2"});
		expectedBook.setPublisher("Test");
		expectedBook.setStars(1);
		
		when(bookService.update(eq("123"), any(BookDto.class))).thenReturn(expectedBook);
		
		Book newBook = new Book();
		newBook.setId(id);
		newBook.setTitle("Test2");
		newBook.setAuthor("Test");
		newBook.setGenre(new String[] {"test1", "test2"});
		newBook.setPublisher("Test");
		newBook.setStars(1);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/api/book/{id}", id)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newBook)))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(expectedBook)));
	}
	
	@Test
	public void shouldReturnError_WhenUpdateBookWithoutTitle() throws Exception {		
		String id = "123";
		
		Book newBook = new Book();
		newBook.setId(id);
		newBook.setAuthor("Test");
		newBook.setGenre(new String[] {"test1", "test2"});
		newBook.setPublisher("Test");
		newBook.setStars(1);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/api/book/{id}", id)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newBook)))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void shouldReturnSuccess_WhenDeleteBook() throws Exception {	
		String id = "123";
	    doNothing().when(bookService).delete(eq(id));
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/book/{id}", id)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
	
	@Test
	public void shouldReturnListBooks_WhenGetBooks() throws Exception {	
		Book book = new Book();
		book.setId("123");
		book.setTitle("Test");
		book.setAuthor("Test");
		book.setGenre(new String[] {"test1", "test2"});
		book.setPublisher("Test");
		book.setStars(1);
		
		List<Book> expectedListBooks = Arrays.asList(book, book);
				
		when(bookService.getBooks()).thenReturn(expectedListBooks);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/books")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(expectedListBooks)));
	}
	
	@Test
	public void shouldReturnBook_WhenGetBookById() throws Exception {
		String id = "123";
		Book expectedBook = new Book();
		expectedBook.setId("123");
		expectedBook.setTitle("Test");
		expectedBook.setAuthor("Test");
		expectedBook.setGenre(new String[] {"test1", "test2"});
		expectedBook.setPublisher("Test");
		expectedBook.setStars(1);
				
		when(bookService.getBookById(eq(id))).thenReturn(expectedBook);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/book/{id}", id)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(expectedBook)));
	}
	
	@Test
	public void shouldReturnError_WhenGetBookByIdWithoutId() throws Exception {		
		String id = "n";
		when(bookService.getBookById(eq(id))).thenThrow(new EntityNotFoundExeceptions("Id not found ["+ id +"]"));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/book/{id}", id)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldReturnListBooks_WhenGetBookByAuthor() throws Exception {
		String title = "Test";
		Book book = new Book();
		book.setId("123");
		book.setTitle("Test");
		book.setAuthor("Test");
		book.setGenre(new String[] {"test1", "test2"});
		book.setPublisher("Test");
		book.setStars(1);
		
		List<Book> expectedListBooks = Arrays.asList(book, book);
				
		when(bookService.getBooksByTitle(eq(title))).thenReturn(expectedListBooks);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/books/{author}", title)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(expectedListBooks)));
	}
	
}
