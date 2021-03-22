package com.hk.bookstore.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CustomerMapper {
    @Select("select cpassword from customersinfo where cusername = #{cusername}")
    public String selectUsernameAndPassword(String cusername);
    @Select("select cid from customersinfo where cusername = #{cusername}")
    public String selectCid(String cusername);
    @Insert("insert into customersinfo (cusername,cpassword) value (#{username},#{password})")
    public void insertCustomer(String username,String password);
}
