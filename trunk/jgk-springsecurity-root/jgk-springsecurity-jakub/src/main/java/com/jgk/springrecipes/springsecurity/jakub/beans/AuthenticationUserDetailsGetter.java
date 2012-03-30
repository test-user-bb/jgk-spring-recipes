package com.jgk.springrecipes.springsecurity.jakub.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="userDetailsService")
public class AuthenticationUserDetailsGetter implements UserDetailsService {
    @Autowired private UserRepository userRepository;
    
    //required by cglib because I use class based aspect weaving
    protected AuthenticationUserDetailsGetter() {
    }
 
    @Autowired
    public AuthenticationUserDetailsGetter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
 
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        User user = userRepository.findByLogin(username);
        throwExceptionIfNotFound(user, username);
        return new AuthenticationUserDetails(user);
    }
 
    private void throwExceptionIfNotFound(User user, String login) {
        if (user == null) {
            throw new UsernameNotFoundException("User with login " + login + "  has not been found.");
        }
    }
}
