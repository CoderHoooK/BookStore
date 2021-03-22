package com.hk.bookstore.entities;

import java.util.Date;

public class Order {
    private Integer oid;
    private Integer cid;
    private Date ptime;
    private String ads;
    private Integer bid;
    private Double sumprice;
    private Integer ostatic;

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public void setPtime(Date ptime) {
        this.ptime = ptime;
    }

    public void setAds(String ads) {
        this.ads = ads;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public void setSumprice(Double sumprice) {
        this.sumprice = sumprice;
    }

    public void setOstatic(Integer ostatic) {
        this.ostatic = ostatic;
    }

    public Integer getOid() {
        return oid;
    }

    public Integer getCid() {
        return cid;
    }

    public Date getPtime() {
        return ptime;
    }

    public String getAds() {
        return ads;
    }

    public Integer getBid() {
        return bid;
    }

    public Double getSumprice() {
        return sumprice;
    }

    public Integer getOstatic() {
        return ostatic;
    }
}
