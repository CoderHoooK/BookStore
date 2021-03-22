package com.hk.bookstore.entities;

public class Customer {
    private Integer cid;
    private String cusername;
    private String cpassowrd;
    private String address;

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public void setCusername(String cusername) {
        this.cusername = cusername;
    }

    public void setCpassowrd(String cpassowrd) {
        this.cpassowrd = cpassowrd;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCid() {
        return cid;
    }

    public String getCusername() {
        return cusername;
    }

    public String getCpassowrd() {
        return cpassowrd;
    }

    public String getAddress() {
        return address;
    }
}
