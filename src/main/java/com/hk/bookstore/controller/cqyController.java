package com.hk.bookstore.controller;

import com.hk.bookstore.entities.Book;
import com.hk.bookstore.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import java.lang.reflect.Array;
import java.util.*;

@Controller
public class cqyController {
    @Autowired
    BookMapper bookMapper;
    public Map<String,Object> selectCategories(String s,Map<String,Object> map,int x){
        ArrayList<Book> list = bookMapper.getBookByCategories(s);
        ArrayList<HashMap>  bookmap = new ArrayList<>();
        int len = list.size();
        int i = (x - 1)*9;
        while(i < len && i < x*9){
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
        return map;
    }
    @GetMapping("/")
    public String indexProd(Map<String,Object> map){
        map = selectCategories("文学",map,1);
        map.put("static",1);
        map.put("page",1);
        map.put("categ","literature");
        return "index";
    }
    @RequestMapping("/literature")
    public String literatureCategories(Map<String,Object> map,@RequestParam("page") int page){
        map = selectCategories("文学",map,page);
        map.put("static",page);
        map.put("page",page);
        map.put("categ","literature");
        return "index";
    }
    @GetMapping("/exercisebooks")
    public String exercisebooksCategories(Map<String,Object> map,@RequestParam("page") int page){
        map = selectCategories("中小学教辅",map,page);
        map.put("static",2);
        map.put("page",page);
        map.put("categ","exercisebooks");
        return "index";
    }
    @GetMapping("/novel")
    public String novelCategories(Map<String,Object> map,@RequestParam("page") int page){
        map = selectCategories("小说",map,page);
        map.put("static",3);
        map.put("page",page);
        map.put("categ","novel");
        return "index";
    }
    @GetMapping("/success")
    public String successCategories(Map<String,Object> map,@RequestParam("page") int page){
        map = selectCategories("成功/励志",map,page);
        map.put("static",4);
        map.put("page",page);
        map.put("categ","success");
        return "index";
    }
    @GetMapping("/exam")
    public String examCategories(Map<String,Object> map,@RequestParam("page") int page){
        map = selectCategories("考试",map,page);
        map.put("static",5);
        map.put("page",page);
        map.put("categ","exam");
        return "index";
    }
    @GetMapping("/history")
    public String historyCategories(Map<String,Object> map,@RequestParam("page") int page){
        map = selectCategories("历史",map,page);
        map.put("static",6);
        map.put("page",page);
        map.put("categ","history");
        return "index";
    }
    @GetMapping("/lang")
    public String langCategories(Map<String,Object> map,@RequestParam("page") int page){
        map = selectCategories("外语",map,page);
        map.put("static",7);
        map.put("page",page);
        map.put("categ","lang");
        return "index";
    }
    @GetMapping("/manager")
    public String managerCategories(Map<String,Object> map,@RequestParam("page") int page){
        map = selectCategories("管理",map,page);
        map.put("static",8);
        map.put("page",page);
        map.put("categ","manager");
        return "index";
    }
    @GetMapping("/literatureofyoung")
    public String literatureofyoungCategories(Map<String,Object> map,@RequestParam("page") int page){
        map = selectCategories("青春文学",map,page);
        map.put("static",9);
        map.put("page",page);
        map.put("categ","literatureofyoung");
        return "index";
    }
    @GetMapping("/law")
    public String lawCategories(Map<String,Object> map,@RequestParam("page") int page){
        map = selectCategories("法律",map,page);
        map.put("static",10);
        map.put("page",page);
        map.put("categ","law");
        return "index";
    }
    @GetMapping("/computer")
    public String computerCategories(Map<String,Object> map,@RequestParam("page") int page){
        map = selectCategories("计算机/网络",map,page);
        map.put("static",11);
        map.put("page",page);
        map.put("categ","computer");
        return "index";
    }
    @GetMapping("/ancientbook")
    public String cancientbookCategories(Map<String,Object> map,@RequestParam("page") int page){
        map = selectCategories("古籍",map,page);
        map.put("static",2);
        map.put("page",page);
        map.put("categ","ancientbook");
        return "index";
    }
    @GetMapping("/result")
    public String result(){
        return "/shopping";
    }


}
