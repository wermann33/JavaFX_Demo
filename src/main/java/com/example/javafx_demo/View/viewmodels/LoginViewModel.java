package com.example.javafx_demo.View.viewmodels;

import com.example.javafx_demo.BL.UserService;

public class LoginViewModel {
    private final UserService userService;

    public LoginViewModel() {
        this.userService = new UserService();
    }

    public boolean checkLogin(String inputUsername, String inputPassword){
        return userService.checkValidUser(inputUsername, inputPassword);
    }
}
