package com.hk.bookstore.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    @Select("select apassword from adminsinfo where ausername=#{username}")
    public String getPassword(String username);
    @Select("select aid from adminsinfo where ausername=#{username}")
    public String getAid(String username);
}
