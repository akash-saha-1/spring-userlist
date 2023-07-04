package com.springmvc.service;

import com.springmvc.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    List<User> list();
}
