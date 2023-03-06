package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {
    public boolean authentication(String username, String password){
        boolean isValidUsername= username.equalsIgnoreCase("samuel");
        boolean isValidPassword= password.equals("2010.Sam");
        return  isValidUsername && isValidPassword;
    }
}
