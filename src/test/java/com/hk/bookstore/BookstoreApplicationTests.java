package com.hk.bookstore;

import com.hk.bookstore.entities.Book;
import com.hk.bookstore.mapper.BookMapper;
import com.hk.bookstore.mapper.CustomerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class BookstoreApplicationTests {

    @Autowired
    CustomerMapper customerMapper;
    @Test
    void contextLoads() {
        System.out.println(customerMapper.selectUsernameAndPassword("hook"));
    }

}
