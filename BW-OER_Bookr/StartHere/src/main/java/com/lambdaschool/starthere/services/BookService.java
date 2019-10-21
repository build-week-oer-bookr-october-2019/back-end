package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface BookService
{
    ArrayList<Book> findAllPageable(Pageable pageable);

    ArrayList<Book> findAll();

    Book findBookById(long id);

//    Book save (Book book);

    void delete(long id);

//    Book update(Book book, long id);

//    void addBookToAuthor(long bookid, long authorid);
}
