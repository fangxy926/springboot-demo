package com.yangman.springbootdemo.mapper;

import com.yangman.springbootdemo.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    Integer addUserOneByOne(User user);

    void addByOneSQL(@Param("users") List<User> users);
}
