package com.hk.bookstore.controller;

import com.hk.bookstore.entities.Book;
import com.hk.bookstore.mapper.AdminMapper;
import com.hk.bookstore.mapper.BookMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BookController {
    @Autowired
    BookMapper bookMapper;
    @Autowired
    AdminMapper adminMapper;
    @RequestMapping("/deletebook")
    public  String deleteBook(HttpSession session, @RequestParam("bid") String bid, Map<String,Object> map){
        bookMapper.deleteBook(bid);
        ArrayList<Book> list = bookMapper.getAllBook();
        ArrayList<HashMap>  bookmap = new ArrayList<>();
        int len = list.size();
        int i = 0;
        while(i < len ){
            HashMap tmp = new HashMap();
            tmp.put("title",list.get(i).getTitle());
            tmp.put("author",list.get(i).getAuthor());
            tmp.put("price",list.get(i).getPrice());
            tmp.put("img",list.get(i).getImg());
            tmp.put("bid",list.get(i).getBid());
            tmp.put("aid",list.get(i).getAid());
            tmp.put("categories",list.get(i).getCategories());
            bookmap.add(tmp);
            i++;
        }
        map.put("books",bookmap);

        return "list";
    }

    @RequestMapping("/updatebook")
    public String upadateBook(@RequestParam("bid") String bid, Map<String,Object> map){
        Book book = bookMapper.getBookById(Integer.parseInt(bid));
        map.put("book",book);
        return "updatebook";
    }
    @RequestMapping("/updating")
    public String updating(Map<String,Object> map,@RequestParam("bid") String bid,@RequestParam("title") String title,@RequestParam("author") String author,@RequestParam("categories") String categories,
                           @RequestParam("img") String img,@RequestParam("price") String price,
                           @RequestParam("intro") String intro
                           ){
        bookMapper.updateBook(bid,title,author,categories,img,price,intro);
        ArrayList<Book> list = bookMapper.getAllBook();
        ArrayList<HashMap>  bookmap = new ArrayList<>();
        int len = list.size();
        int i = 0;
        while(i < len ){
            HashMap tmp = new HashMap();
            tmp.put("title",list.get(i).getTitle());
            tmp.put("author",list.get(i).getAuthor());
            tmp.put("price",list.get(i).getPrice());
            tmp.put("img",list.get(i).getImg());
            tmp.put("bid",list.get(i).getBid());
            tmp.put("aid",list.get(i).getAid());
            tmp.put("categories",list.get(i).getCategories());
            bookmap.add(tmp);
            i++;
        }
        map.put("books",bookmap);
        return "list";
    }
    @RequestMapping("/addbook")
    public String addBook(){
        return "addbook";
    }
    @RequestMapping("/adding")
    public String adding(HttpSession session,Map<String,Object> map,@RequestParam("title") String title,@RequestParam("author") String author,@RequestParam("categories") String categories,
                          @RequestParam("img") String img,@RequestParam("price") String price,
                          @RequestParam("intro") String intro){
        bookMapper.insertBook(Integer.parseInt(session.getAttribute("aid").toString()),title,author,categories,img,price,intro);
        ArrayList<Book> list = bookMapper.getAllBook();
        ArrayList<HashMap>  bookmap = new ArrayList<>();
        int len = list.size();
        int i = 0;
        while(i < len ){
            HashMap tmp = new HashMap();
            tmp.put("title",list.get(i).getTitle());
            tmp.put("author",list.get(i).getAuthor());
            tmp.put("price",list.get(i).getPrice());
            tmp.put("img",list.get(i).getImg());
            tmp.put("bid",list.get(i).getBid());
            tmp.put("aid",list.get(i).getAid());
            tmp.put("categories",list.get(i).getCategories());
            bookmap.add(tmp);
            i++;
        }
        map.put("books",bookmap);
        return "list";
    }
}
