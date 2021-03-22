package com.hk.bookstore.controller;

import com.hk.bookstore.entities.Book;
import com.hk.bookstore.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class searchController {
    //首页搜索
    @Autowired
    BookMapper bookMapper;
    @RequestMapping(value = "/search")
    public String seachBox(@RequestParam("cte") String cte, Map<String,Object> map){
        ArrayList<Book> list = bookMapper.getBookByTitle(cte);
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
        map.put("static",0);
        map.put("page",1);
        map.put("categ","search");
        return "/index";
    }
}
