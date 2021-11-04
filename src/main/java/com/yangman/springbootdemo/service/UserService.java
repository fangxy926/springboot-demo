package com.yangman.springbootdemo.service;

import com.yangman.springbootdemo.bean.User;

import java.util.List;

public interface UserService {

    public void addUserOneByOne(List<User> userList);

    public void addByOneSQL(List<User> users);
}
