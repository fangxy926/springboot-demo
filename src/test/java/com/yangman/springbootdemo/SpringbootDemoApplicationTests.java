package com.yangman.springbootdemo;

import com.yangman.springbootdemo.bean.User;
import com.yangman.springbootdemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringbootDemoApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
    }


    /**
     * 单元测试加事务的目的是为了插入之后自动回滚，避免影响下一次测试结果
     * 一条一条插入
     */
    @Test
    @Transactional
    void addUserOneByOne() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            User u = new User();
            u.setAddress("广州：" + i);
            u.setUsername("张三：" + i);
            u.setPassword("123：" + i);
            users.add(u);
        }
        userService.addUserOneByOne(users);
    }


    /**
     * 合并成一条 SQL 插入
     */
    @Test
    @Transactional
    void addByOneSQL() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            User u = new User();
            u.setAddress("广州：" + i);
            u.setUsername("张三：" + i);
            u.setPassword("123：" + i);
            users.add(u);
        }
        userService.addByOneSQL(users);
    }
}
