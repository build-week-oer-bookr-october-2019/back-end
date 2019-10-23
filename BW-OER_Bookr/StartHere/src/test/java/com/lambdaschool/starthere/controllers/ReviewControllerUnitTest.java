package com.lambdaschool.starthere.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.Review;
import com.lambdaschool.starthere.models.User;
import com.lambdaschool.starthere.services.BookService;
import com.lambdaschool.starthere.services.ReviewService;
import com.lambdaschool.starthere.services.UserService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ReviewController.class, secure = false)
public class ReviewControllerUnitTest
{
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private ReviewService reviewService;

    @MockBean
    private BookService bookService;

    private List<Review> reviewList;

    @Before
    public void setUp() throws Exception
    {
        //MAKE A LIST FOR REVIEWS
        reviewList = new ArrayList<>();

        //MAKE BOOKS
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();

        //MAKE REVIEWS
        Review r1 = new Review("Johnny", "Terrible book!",book1);
        Review r2 = new Review("John", "Terrible book.",book2);
        Review r3 = new Review("Sam", "Great book!",book3);

        //GIVE IDS, AND BOOKS TO REVIEWS
        r1.setReviewid(1);
        r1.setBook(book1);
        r2.setReviewid(2);
        r2.setBook(book2);
        r3.setReviewid(3);
        r3.setBook(book3);


        //ADD REVIEWS TO REVIEW LIST
        reviewList.add(r1);
        reviewList.add(r2);
        reviewList.add(r3);

    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void listAllReviews() throws Exception
    {
        String apiUrl = "/reviews/reviews";

//        String courseName = "JavaScript";
//        Instructor instructor = new Instructor("John");
//        Course c1 = new Course(courseName,instructor);

        Mockito.when(reviewService.findAll()).thenReturn((ArrayList<Review>) reviewList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);

        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(reviewList);

        assertEquals(er, tr);
    }

    @Test
    public void getReviewById() throws Exception
    {
        String apiUrl = "/reviews/review/12";

        Mockito.when(reviewService.findReviewById(12)).thenReturn(reviewList.get(1));

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(reviewList.get(1));

        System.out.println("Expect: " + er);
        System.out.println("Actual: " + tr);

        assertEquals("Rest API Returns List", er, tr);
    }

//    @Test
//    public void addNewReview() throws Exception
//    {
//        String apiUrl = "/review/book/{bookid}";
//
//        // build a restaurant
//        //        ArrayList<RestaurantPayments> thisPay = new ArrayList<>();
//        String reviewer = "Marta";
//        String review = "This book is very good.";
//        Book book1 = new Book();
//
//        Review reviewA = new Review(reviewer, review, book1);
//
//        reviewA.setReviewid(4);
//        book1.setBookid(100);
//        ObjectMapper mapper = new ObjectMapper();
//        String courseString = mapper.writeValueAsString(reviewA);
//
////        newReview.setBook(bookService.findBookById(bookid));
////          reviewService.save(newReview);
//        Mockito.when(reviewService.save(any(Review.class))).thenReturn(reviewA);
//        Mockito.when(reviewService.save(any(Review.class))).thenReturn(reviewA);
//
//        RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl)
//                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
//                .content(courseString);
//        mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
//    }

    @Test
    public void deleteReviewById()throws Exception
    {
        String apiUrl = "/data/reviews/{reviewid}";

        RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl, "3")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(rb)
                .andExpect(status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }
}
