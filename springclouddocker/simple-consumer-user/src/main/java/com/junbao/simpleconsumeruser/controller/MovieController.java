package com.junbao.simpleconsumeruser.controller;

import com.junbao.simpleconsumeruser.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: ganfengbao
 * @Date: 2019/1/1 16:11
 * @Version 1.0
 */
@RestController
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        return  this.restTemplate.getForObject("http://localhost:8000/" + id, User.class);
    }

}
