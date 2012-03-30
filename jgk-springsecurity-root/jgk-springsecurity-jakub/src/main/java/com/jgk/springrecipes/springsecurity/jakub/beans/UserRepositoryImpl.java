package com.jgk.springrecipes.springsecurity.jakub.beans;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository(value="userRepository")
public class UserRepositoryImpl implements UserRepository {

    @Override
    public User findByLogin(String username) {
        System.out.println(String.format("findByLogin(%s)",username));
        return null;
    }

}
