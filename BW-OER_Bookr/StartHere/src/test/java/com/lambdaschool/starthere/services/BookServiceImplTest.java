package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.StartHereApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartHereApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class BookServiceImplTest
{
    @Autowired
    private BookService bookService;

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
        assertEquals(10, bookService.findAll().size());
    }

    @Test
    public void findAllPageable()
    {

    }

    @Test
    public void B_findBookById()
    {
        assertEquals("Chemistry: The Molecular Nature of Matter and Change", bookService.findBookById(2).getTitle());
    }

    @Test
    public void C_delete()
    {
        bookService.delete(1);
        assertEquals(9, bookService.findAll().size());
    }
}