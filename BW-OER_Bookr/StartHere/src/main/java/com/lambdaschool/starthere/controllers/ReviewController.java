package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.Review;
import com.lambdaschool.starthere.services.BookService;
import com.lambdaschool.starthere.services.ReviewService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/")
public class ReviewController
{
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private BookService bookService;

    @ApiOperation(value= "return all Reviews", response= Review.class, responseContainer = "List")

    @ApiImplicitParams({
                               @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                                                 value = "Results page you want to retrieve (0..N)"),
                               @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                                                 value = "Number of records per page."),
                               @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                                                 value = "Sorting criteria in the format: property(,asc|desc). " +
                                                         "Default sort order is ascending. " +
                                                         "Multiple sort criteria are supported.")})
    //http://localhost:2019/reviews/reviews/paging/?page=1&size=10
    //http://localhost:2019/reviews/reviews/paging/?sort=city&sort=name
    @GetMapping(value = "reviews/reviews/paging", produces = {"application/json"})
    public ResponseEntity<?> listAllReviewsByPage(@PageableDefault(page=0,
                                                                 size=3)
                                                        Pageable pageable)
    {
        ArrayList<Review> myReviews = reviewService.findAllPageable(pageable);
        return new ResponseEntity<>(myReviews, HttpStatus.OK);
    }


    @GetMapping(value = "reviews/reviews", produces = {"application/json"})
    public ResponseEntity<?> listAllReviews()
    {
        ArrayList<Review> myReviews = reviewService.findAll();
        return new ResponseEntity<>(myReviews, HttpStatus.OK);
    }

    //http://localhost:2019/reviews/review/2
    @GetMapping(value = "reviews/review/{reviewid}",
                produces = {"application/json"})
    public ResponseEntity<?> getReviewById(
            @PathVariable
                    Long reviewid)
    {
        Review r = reviewService.findReviewById(reviewid);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    // POST http://localhost:2019/review/book/1
    @PostMapping(value = "/review/book/{bookid}",
                 consumes = {"application/json"},
                 produces = {"application/json"})
    public ResponseEntity<?> addNewReview(@Valid
                                        @RequestBody
                                                Review newReview,
                                        @PathVariable long bookid)
    {   newReview.setBook(bookService.findBookById(bookid));
        reviewService.save(newReview);
        return new ResponseEntity<>(HttpStatus.CREATED);
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

    // http://localhost:2019/data/reviews/1
    @DeleteMapping("data/reviews/{reviewid}")
    public ResponseEntity<?> deleteReviewById(
            @PathVariable
                    long reviewid)
    {
        reviewService.delete(reviewid);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
