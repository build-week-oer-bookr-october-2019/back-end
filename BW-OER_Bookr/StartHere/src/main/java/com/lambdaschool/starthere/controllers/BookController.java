package com.lambdaschool.starthere.controllers;


import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.services.BookService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/")
public class BookController
{
    @Autowired
    private BookService bookService;

//    @Autowired
//    private AuthorService authorService;

    @ApiOperation(value= "return all Books", response= Book.class, responseContainer = "List")

    @ApiImplicitParams({
                               @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                                                 value = "Results page you want to retrieve (0..N)"),
                               @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                                                 value = "Number of records per page."),
                               @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                                                 value = "Sorting criteria in the format: property(,asc|desc). " +
                                                         "Default sort order is ascending. " +
                                                         "Multiple sort criteria are supported.")})
    //http://localhost:2019/books/books/paging/?page=1&size=10
    //http://localhost:2019/books/books/paging/?sort=city&sort=name
    @GetMapping(value = "books/books/paging", produces = {"application/json"})
    public ResponseEntity<?> listAllBooksByPage(@PageableDefault(page=0,
                                                                   size=3)
                                                          Pageable pageable)
    {
        ArrayList<Book> myBooks = bookService.findAllPageable(pageable);
        return new ResponseEntity<>(myBooks, HttpStatus.OK);
    }


    @GetMapping(value = "books/books", produces = {"application/json"})
    public ResponseEntity<?> listAllBooks()
    {
        ArrayList<Book> myBooks = bookService.findAll();
        return new ResponseEntity<>(myBooks, HttpStatus.OK);
    }

//    // http://localhost:2019/data/books/15/authors/2
//    @PostMapping("/data/books/{bookid}/authors/{authorid}")
//    public ResponseEntity<?> postBookAuthorByIds(HttpServletRequest request,
//                                               @PathVariable
//                                                       long bookid,
//                                               @PathVariable
//                                                       long authorid)
//    {
//
//        bookService.addBookToAuthor(bookid,
//                authorid);
//
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @PutMapping(value = "data/books/{bookid}")
//    public ResponseEntity<?> updateBook(
//            @RequestBody
//                    Book updateBook,
//            @PathVariable
//                    long bookid)
//    {
//        bookService.update(updateBook, bookid);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    // http://localhost:2019/data/books/1
    @DeleteMapping("data/books/{bookid}")
    public ResponseEntity<?> deleteBookById(
            @PathVariable
                    long bookid)
    {
        bookService.delete(bookid);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
