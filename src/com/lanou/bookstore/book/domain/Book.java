package com.lanou.bookstore.book.domain;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Created by dllo on 17/9/22.
 */
public class Book implements Serializable {
    private String bid;
    private String bname;
    private double price;
    private String author;
    private String image;
    private String cid;
    private boolean del;
    private int count;
    private int sal;

    public Book() {
    }

    public Book(String bid, String bname, double price, String author, String image, String cid, boolean del, int count, int sal) {
        this.bid = bid;
        this.bname = bname;
        this.price = price;
        this.author = author;
        this.image = image;
        this.cid = cid;
        this.del = del;
        this.count = count;
        this.sal = sal;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }
}
