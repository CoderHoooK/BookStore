package com.hk.bookstore.mapper;

import com.hk.bookstore.entities.Book;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface BookMapper {
    @Select("select * from bookinfo where categories = #{categories} ")
    public ArrayList<Book> getBookByCategories(String categories);
    @Select("select * from bookinfo where bid = #{id} ")
    public Book getBookById(Integer id);
    @Select("select * from bookinfo where title like '%${cte}%'")
    public ArrayList<Book> getBookByTitle(String cte);
    @Select("select * from bookinfo")
    public ArrayList<Book> getAllBook();
    @Delete("Delete from bookinfo where bid = #{bid}")
    public void deleteBook(String bid);
    @Update("update bookinfo set title = #{title} , author = #{author},categories=#{categories},img = #{img},price = #{price}, intro = #{intro} where bid = #{bid}")
    public void updateBook(String bid,String title,String author,String categories, String img, String price,String intro);
    @Insert("insert into bookinfo (title,author,categories,img,price,intro,aid) value (#{title} , #{author},#{categories},${img},#{price},#{intro},#{aid})")
    public void insertBook(int aid,String title,String author,String categories, String img, String price,String intro);
}
