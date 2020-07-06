package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface BookService
{
    ArrayList<Book> findAllPageable(Pageable pageable);

    ArrayList<Book> findAll();

    Book findBookById(long id);

    void delete(long id);

}
