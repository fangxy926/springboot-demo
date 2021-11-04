package com.yangman.springbootdemo.service.impl;

import com.yangman.springbootdemo.bean.User;
import com.yangman.springbootdemo.mapper.UserMapper;
import com.yangman.springbootdemo.service.UserService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private SqlSessionFactory sqlSessionFactory;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUserOneByOne(List<User> userList) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        UserMapper um = sqlSession.getMapper(UserMapper.class);
        long startTime = System.currentTimeMillis();
        for (User user : userList) {
            um.addUserOneByOne(user);
        }
        sqlSession.commit();
        long endTime = System.currentTimeMillis();
        logger.info("一条条插入 SQL 耗费时间 {}", (endTime - startTime));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addByOneSQL(List<User> users) {
        long startTime = System.currentTimeMillis();
        userMapper.addByOneSQL(users);
        long endTime = System.currentTimeMillis();
        logger.info("合并成一条 SQL 插入耗费时间 {}", (endTime - startTime));
    }
}
