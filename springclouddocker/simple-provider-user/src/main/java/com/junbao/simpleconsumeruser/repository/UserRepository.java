package com.junbao.simpleconsumeruser.repository;

import com.junbao.simpleconsumeruser.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: ganfengbao
 * @Date: 2019/1/1 15:53
 * @Version 1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
