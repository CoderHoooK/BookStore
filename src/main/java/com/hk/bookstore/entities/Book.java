package com.hk.bookstore.entities;

public class Book {
    private Integer bid;
    private String title;
    private String author;
    private String categories;
    private String img;
    private String intro;
    private Double price;
    private Integer aid;

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getBid() {
        return bid;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategories() {
        return categories;
    }

    public String getImg() {
        return img;
    }

    public String getIntro() {
        return intro;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getAid() {
        return aid;
    }
}
