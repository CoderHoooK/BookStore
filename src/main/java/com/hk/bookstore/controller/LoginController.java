package com.hk.bookstore.controller;

import com.hk.bookstore.entities.Book;
import com.hk.bookstore.mapper.AdminMapper;
import com.hk.bookstore.mapper.BookMapper;
import com.hk.bookstore.mapper.CustomerMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller

public class LoginController {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    BookMapper bookMapper;
    @Autowired
    CustomerMapper customerMapper;
    @RequestMapping("/loginb")
    public String loginb(@RequestParam("username") String username, @RequestParam("password") String password, Map<String,Object> map, HttpSession session){
        String s = customerMapper.selectUsernameAndPassword(username);
        if(!StringUtils.isEmpty(username) && s.equals(password)){
            session.setAttribute("loginUser",username);
            session.setAttribute("cid",customerMapper.selectCid(username));
            //登录成功
            //跳转到bashboard,因为是thymeleaf的关系,所以不用加上.html
            ArrayList<Book> list = bookMapper.getBookByCategories("文学");
            ArrayList<HashMap>  bookmap = new ArrayList<>();
            int len = list.size();
            int i = 0;
            while(i < len && i < 9){
                HashMap tmp = new HashMap();
                tmp.put("title",list.get(i).getTitle());
                tmp.put("author",list.get(i).getAuthor());
                tmp.put("price",list.get(i).getPrice());
                tmp.put("img",list.get(i).getImg());
                tmp.put("bid",list.get(i).getBid());
                bookmap.add(tmp);
                i++;
            }
            map.put("books",bookmap);
            map.put("static",1);
            map.put("page",1);
            map.put("categ","literature");
            return "redirect:/";
        }else{
            map.put("msg","用户名密码错误");
            return "login";
        }

    }
    @RequestMapping("/login")
    public String login(Map<String,Object> map){
        return "login";
    }

    @RequestMapping("/signup")
    public String signup(){
        return "/signup";
    }
    @RequestMapping("/spsuccess")
    public String success(@RequestParam("username") String username,@RequestParam("password") String password){
        customerMapper.insertCustomer(username,password);
        return "/login";
    }
    @RequestMapping("/adminlogin")
    public String adminlogin(Map<String,Object> map){
        return "adminlogin";
    }
    @RequestMapping("/adminloginb")
    public String adminloginb(@RequestParam("username") String username, @RequestParam("password") String password, Map<String,Object> map, HttpSession session){
        String s = adminMapper.getPassword(username);
        if(!StringUtils.isEmpty(username) && s.equals(password)){
            session.setAttribute("loginUser",username);
            session.setAttribute("aid",adminMapper.getAid(username));
            //登录成功
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
            return "/list";
        }else{
            map.put("msg","用户名密码错误");
            return "adminlogin";
        }

    }

}
