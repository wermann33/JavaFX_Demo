package com.example.javafx_demo.View.viewmodels;

import com.example.javafx_demo.BL.IUserService;
import com.example.javafx_demo.BL.UserService;
import com.example.javafx_demo.DefaultInjector;
import com.example.javafx_demo.Injectable;

public class LoginViewModel implements Injectable {
    private final IUserService userService;

    public LoginViewModel() {

        this.userService = DefaultInjector.getService(UserService.class);
    }

    public boolean checkLogin(String inputUsername, String inputPassword){
        return userService.checkValidUser(inputUsername, inputPassword);
    }
}
