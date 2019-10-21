package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value="Book", description = "The Book Entity")
@Entity
@Table(name = "book")
public class Book extends Auditable
{
    @ApiModelProperty(name = "bookid",
                      value = "primary key for the Book",
                      required = true,
                      example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookid;

    @ApiModelProperty(name = "title",
                      value = "title of the Book",
                      example = "Calculus II")
    private String title;

    @ApiModelProperty(name = "author",
                      value = "author of the Book",
                      example = "Joe Jones")
    private String author;

    @ApiModelProperty(name = "publisher",
                      value = "publisher of the Book",
                      example = "Pearson")
    private String publisher;

    @ApiModelProperty(name = "license",
                      value = "license of the Book",
                      example = "License")
    private String license;


    @ApiModelProperty(name = "reviews", value = "review for the textbook", example = "Calling Texas Home")
    @OneToMany(mappedBy = "book")
    @JsonIgnoreProperties("books")
    private List<Review> reviews = new ArrayList<>();

    public Book()
    {
    }

    public Book(String title, String author, String publisher, String license, List<Review> reviews)
    {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.license = license;
        this.reviews = reviews;
    }

    public long getBookid()
    {
        return bookid;
    }

    public void setBookid(long bookid)
    {
        this.bookid = bookid;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getPublisher()
    {
        return publisher;
    }

    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    public String getLicense()
    {
        return license;
    }

    public void setLicense(String license)
    {
        this.license = license;
    }

    public List<Review> getReviews()
    {
        return reviews;
    }

    public void setReviews(List<Review> reviews)
    {
        this.reviews = reviews;
    }
}