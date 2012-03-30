package com.jgk.springrecipes.springsecurity.jakub.beans;

import org.springframework.security.core.userdetails.User;

public interface UserRepository {

    User findByLogin(String username);

}
