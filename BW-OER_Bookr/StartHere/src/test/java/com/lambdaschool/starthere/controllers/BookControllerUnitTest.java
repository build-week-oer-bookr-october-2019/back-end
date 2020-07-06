package com.lambdaschool.starthere.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.services.BookService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BookController.class, secure = false)
public class BookControllerUnitTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private List<Book> bookList;

    @Before
    public void setUp() throws Exception
    {
        //MAKE A LIST FOR BOOKS
        bookList = new ArrayList<>();

        //MAKE REVIEWS
        ArrayList review1 = new ArrayList<>();
        ArrayList review2 = new ArrayList<>();
        ArrayList review3 = new ArrayList<>();

        //MAKE BOOKS
        Book b1 = new Book("Calculus II", "Jones Buck", "Publisher 1", "License 1", review1);
        Book b2 = new Book("Calculus II", "Joseph Buck", "Publisher 1", "License 1", review2);
        Book b3 = new Book("Calculus III", "Jose Buck", "Publisher 1", "License 1", review3);

        //GIVE IDS, AND BOOKS TO REVIEWS
        b1.setBookid(1);
        b1.setReviews(review1);
        b2.setBookid(2);
        b2.setReviews(review2);
        b3.setBookid(3);
        b3.setReviews(review3);


        //ADD REVIEWS TO REVIEW LIST
        bookList.add(b1);
        bookList.add(b2);
        bookList.add(b3);

    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void listAllBooks() throws Exception
    {
        String apiUrl = "/books/books";

        Mockito.when(bookService.findAll()).thenReturn((ArrayList<Book>) bookList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);

        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(bookList);

        assertEquals(er, tr);
    }

    @Test
    public void getBookById() throws Exception
    {
        String apiUrl = "/books/book/12";

        Mockito.when(bookService.findBookById(12)).thenReturn(bookList.get(1));

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(bookList.get(1));

        System.out.println("Expect: " + er);
        System.out.println("Actual: " + tr);

        assertEquals("Rest API Returns List", er, tr);
    }


    @Test
    public void deleteBookById()throws Exception
    {
        String apiUrl = "/data/books/{bookid}";

        RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl, "3")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(rb)
                .andExpect(status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }
}
