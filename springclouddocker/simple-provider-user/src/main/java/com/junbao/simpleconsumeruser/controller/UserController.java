package com.junbao.simpleconsumeruser.controller;

import com.junbao.simpleconsumeruser.entity.User;
import com.junbao.simpleconsumeruser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ganfengbao
 * @Date: 2019/1/1 15:54
 * @Version 1.0
 */

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        User findOne = this.userRepository.findOne(id);
        return findOne;
    }
}
