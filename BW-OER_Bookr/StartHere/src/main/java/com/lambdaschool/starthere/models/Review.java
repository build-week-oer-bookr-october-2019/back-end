package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;

@ApiModel(value="Review", description = "The Review Entity")
@Entity
@Table(name = "review")
public class Review extends Auditable
{
    @ApiModelProperty(name = "reviewid",
                      value = "primary key for the Review",
                      required = true,
                      example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reviewid;

    @ApiModelProperty(name = "reviewer",
                      value = "Author of Review",
                      example = "Mike James")
    private String reviewer;

    @ApiModelProperty(name = "review",
                      value = "Review of the Textbook",
                      example = "This textbook is great.")
    private String review;

    @ApiModelProperty(name = "book", value = "Book that the review is for", required = true, example = "Calculus III")
    @ManyToOne
    @JoinColumn(name = "bookid")
    @JsonIgnoreProperties("reviews")
    private Book book;

    public Review()
    {
    }

    public Review(String reviewer, String review, Book book)
    {
        this.reviewer = reviewer;
        this.review = review;
        this.book = book;
    }

    public long getReviewid()
    {
        return reviewid;
    }

    public void setReviewid(long reviewid)
    {
        this.reviewid = reviewid;
    }

    public String getReviewer()
    {
        return reviewer;
    }

    public void setReviewer(String reviewer)
    {
        this.reviewer = reviewer;
    }

    public String getReview()
    {
        return review;
    }

    public void setReview(String review)
    {
        this.review = review;
    }

    public Book getBook()
    {
        return book;
    }

    public void setBook(Book book)
    {
        this.book = book;
    }
}