package com.hk.bookstore.controller;

import com.hk.bookstore.Service.PayService;
import com.hk.bookstore.entities.Book;
import com.hk.bookstore.entities.Order;
import com.hk.bookstore.mapper.BookMapper;
import com.hk.bookstore.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ShoppingcartController {
    @Autowired
    BookMapper bookMapper;
    @Autowired
    OrderMapper orderMapper;
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
    @RequestMapping("/order")
    public String addShoppingcart(Map<String,Object> map, @RequestParam("bid") int bid, @RequestParam("cid") int cid){
        map = selectCategories("文学",map,1);
        map.put("static",1);
        map.put("page",1);
        map.put("categ","literature");
//      把为购物车里的订单添加到orderinfo表中 , 状态为0 表明未支付
        Date date = new Date(); // this object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(date));
        double price = bookMapper.getBookById(bid).getPrice();
        orderMapper.addShoppingcart(cid,formatter.format(date),bid,price);
        return "index";
    }
    public Map<String,Object> selectOrderByCid(Map<String,Object> map,int cid,int sta){
        ArrayList<Order> order =  orderMapper.getShoppingcartList(cid,sta);
        ArrayList<HashMap>  bookmap = new ArrayList<>();
        int i = 0;
        while(i < order.size()){
            int bid = order.get(i).getBid();
            Book book = bookMapper.getBookById(bid);
            HashMap<String,Object> tmp = new HashMap<>();
            tmp.put("title",book.getTitle());
            tmp.put("author",book.getAuthor());
            tmp.put("price",book.getPrice());
            tmp.put("img",book.getImg());
            tmp.put("bid",book.getBid());
            int oid = order.get(i).getOid();
            tmp.put("oid",oid);
            bookmap.add(tmp);
            i++;
        }
        map.put("books",bookmap);
        return map;
    }
    @RequestMapping("/shoppingcart")
    public String cartList(Map<String,Object> map,int cid){
        map = selectOrderByCid(map,cid,0);
        return"shoppingcart";
    }

    @PostMapping("/orderad")
    public String payForBooks(HttpSession session, Map<String,Object> map, @RequestParam("checkbox[]") int checkbox[]){
        double sum = 0;
        for(int i = 0 ; i < checkbox.length; i++){
            Order order = orderMapper.getOrderList(checkbox[i]);
            orderMapper.updateStatic(checkbox[i]);
            sum+=order.getSumprice();
        }
        session.setAttribute("sumprice",sum);
        return "wtad";
    }


    @GetMapping(value = "/pay", produces = {"text/html;charset=UTF-8"})
    public String pay(HttpSession session, Model model) throws Exception {
        Date date = new Date(); // this object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orderMapper.addOrder(Integer.parseInt(session.getAttribute("cid").toString()),0,"adf",Double.parseDouble(session.getAttribute("sumprice").toString()));
        PayService payService = new PayService();
        ArrayList<Order> o = orderMapper.getOrder();

        Object html = payService.pay("测试", Integer.toString(o.get(o.size()-1).getOid()),session.getAttribute("sumprice").toString());
        model.addAttribute("html",html);
        return "pay";
    }
    @GetMapping("/odelete")
    public String deleteOrder(Map<String,Object> map,HttpSession session,@RequestParam("oid") int oid) {
        orderMapper.delectByOid(oid);
        int cid = Integer.parseInt(session.getAttribute("cid").toString());
        map = selectOrderByCid(map,cid,0);
        return "shoppingcart";
    }
    @RequestMapping("/orderinfo")
    public String orderList(Map<String,Object> map,HttpSession session){
        map = selectOrderByCid(map,Integer.parseInt(session.getAttribute("cid").toString()),1);
        return "order";
    }
}
