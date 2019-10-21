package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.Review;
import com.lambdaschool.starthere.repository.BookRepository;
import com.lambdaschool.starthere.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service(value = "reviewService")
public class ReviewServiceImpl implements ReviewService
{
    @Autowired
    private ReviewRepository reviewrepos;

    //    @Autowired
    //    private AuthorRepository authorrepos;

    @Override
    public ArrayList<Review> findAll()
    {
        ArrayList<Review> list = new ArrayList<>();
        reviewrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public ArrayList<Review> findAllPageable(Pageable pageable)
    {
        ArrayList<Review> list = new ArrayList<>();
        reviewrepos.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

        @Override
        public Review findReviewById(long id) throws EntityNotFoundException
        {
            return reviewrepos.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        }

    @Override
    public Review save(Review review)
    {
        //        Todo newTodo = new Todo();

        //        newTodo.setDescription(todo.getDescription());
        //        newTodo.setDatestarted(todo.getDatestarted());
        //        newTodo.setCompleted(todo.isCompleted());

        return reviewrepos.save(review);
    }
    //
    //    @Override
    //    public void addBookToAuthor(long bookid, long authorid)
    //    {
    //        bookrepos.findById(bookid)
    //                .orElseThrow(() -> new ResourceNotFoundException("User id " + bookid + " not found!"));
    //        authorrepos.findById(authorid)
    //                .orElseThrow(() -> new ResourceNotFoundException("Role id " + authorid + " not found!"));
    //
    //           authorrepos.insertBookToAuthor(bookid,
    //                    authorid);
    //
    //
    //    }
    //
    //    @Override
    //    public Book update(Book book, long id)
    //    {
    //        Book currentBook = bookrepos.findById(id)
    //                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    //
    //        if (book.getTitle() != null)
    //        {
    //            currentBook.setTitle(book.getTitle());
    //        }
    //
    //        if (book.getISBN() != null)
    //        {
    //            currentBook.setISBN(book.getISBN());
    //        }
    //
    //        if (book.getCopy() != 100000)
    //        {
    //            currentBook.setCopy(book.getCopy());
    //        }
    //
    //        return bookrepos.save(currentBook);
    //
    //        return null;
    //    }

    @Transactional
    @Override
    public void delete(long id)
    {
        reviewrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review id " + id + " not found!"));
        reviewrepos.deleteById(id);
    }

}
