package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Review;
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

        return reviewrepos.save(review);
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        reviewrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review id " + id + " not found!"));
        reviewrepos.deleteById(id);
    }

}
