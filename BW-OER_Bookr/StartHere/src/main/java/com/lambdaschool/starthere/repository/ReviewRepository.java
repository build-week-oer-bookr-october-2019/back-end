package com.lambdaschool.starthere.repository;


import com.lambdaschool.starthere.models.Review;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReviewRepository extends PagingAndSortingRepository<Review, Long>
{
}
