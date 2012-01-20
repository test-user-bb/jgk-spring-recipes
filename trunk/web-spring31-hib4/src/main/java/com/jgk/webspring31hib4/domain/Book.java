package com.jgk.webspring31hib4.domain;

import java.io.Serializable;

import com.jgk.webspring31hib4.enumeration.BookType;


public class Book implements Serializable {

    private static final long serialVersionUID = -1406533541850995714L;
    private Integer id;
    private String title;
    private String author;
    private BookType bookType;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public BookType getBookType() {
        return bookType;
    }
    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }
    
    
}
