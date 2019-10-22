package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.StartHereApplication;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.Review;
import com.lambdaschool.starthere.models.UserRoles;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartHereApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class ReviewServiceImplTest
{
    @Autowired
    private ReviewService reviewService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void A_findAll()
    {
        assertEquals(10, reviewService.findAll().size());
    }

    @Test
    public void findAllPageable()
    {
    }

    @Test
    public void B_findReviewById()
    {
        assertEquals("Great textbook.", reviewService.findReviewById(1).getReview());
    }

//    @Test
//    public void C_save()
//    {
//        Book book = new Book();
//        Review r1 = new Review("John", "Amazing book!", book);
//        r1.setReviewid(10);
//
//        reviewService.save(r1);
//        Assert.assertEquals(6, reviewService.findAll().size());
//    }

    @Test
    public void D_delete()
    {
        reviewService.delete(1);
        assertEquals(9, reviewService.findAll().size());
    }
}