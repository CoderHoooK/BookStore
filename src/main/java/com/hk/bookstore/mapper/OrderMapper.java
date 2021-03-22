package com.hk.bookstore.mapper;

import com.hk.bookstore.entities.Order;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.Date;

@Mapper
public interface OrderMapper {
    @Insert("insert into ordersinfo (cid,bid,sumprice,static,otime) values (#{cid},#{bid},#{sumprice},0,'${otime}')")
    public void addShoppingcart(int cid, String otime,int bid,double sumprice);
    @Select("select * from ordersinfo where cid = #{cid} and static = #{sta}")
    public ArrayList<Order> getShoppingcartList(int cid,int sta);
    @Select("select * from ordersinfo where static = 3")
    public ArrayList<Order> getOrder();
    @Select("select * from ordersinfo where oid = #{oid}")
    public Order getOrderList(int oid);
    @Delete("delete from ordersinfo where oid=#{oid}")
    public void delectByOid(int oid);
    @Delete("delete from ordersinfo where bid=#{oid}")
    public void delectByBid(int bid);
    @Update("update ordersinfo set static = 1 where oid=#{oid}")
    public void updateStatic(int oid);
    @Insert("insert into ordersinfo (cid,bid,ads,sumprice,static) values (#{cid},#{bid},'${ads}',#{sumprice},3)")
    public void addOrder(int cid,int bid,String ads,double sumprice);
}