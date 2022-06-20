package com.example.backniznes;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class MyUserDetails{
    private String userName;
    private String password;

    public MyUserDetails(String userName,String password) {
        this.userName = userName;
        this.password = password;
    }
}
