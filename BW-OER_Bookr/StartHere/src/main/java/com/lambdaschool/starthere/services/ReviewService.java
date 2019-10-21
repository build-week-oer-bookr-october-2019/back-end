package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Review;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface ReviewService
{
    ArrayList<Review> findAllPageable(Pageable pageable);

    ArrayList<Review> findAll();

    Review findReviewById(long id);

    Review save (Review review);

    void delete(long id);

    //    Book update(Book book, long id);

    //    void addBookToAuthor(long bookid, long authorid);
}
