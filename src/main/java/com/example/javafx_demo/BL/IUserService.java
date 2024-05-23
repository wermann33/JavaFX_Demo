package com.example.javafx_demo.BL;

import com.example.javafx_demo.BL.models.UserModel;

import java.util.List;

public interface IUserService {
    boolean checkValidUser(String username, String password);
    List<UserModel> getAllUsers();
    void addUser(UserModel user);
    void deleteUser(UserModel userModel);
}
