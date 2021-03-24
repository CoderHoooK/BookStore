package com.hk.bookstore;

import com.hk.bookstore.entities.Book;
import com.hk.bookstore.mapper.BookMapper;
import com.hk.bookstore.mapper.CustomerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Math.abs;

@SpringBootTest
class BookstoreApplicationTests {

    @Autowired
    CustomerMapper customerMapper;
    @Test
    void contextLoads() {
        int count = 0;
        for(int i =0; i < 100; i ++)
            count = count++;
        System.out.println(count);


    }

}
