package com.example.javafx_demo.BL;
import com.example.javafx_demo.DAL.repositories.UserDAO;

import com.example.javafx_demo.BL.models.UserModel;
import com.example.javafx_demo.DefaultInjector;
import com.example.javafx_demo.Injectable;

import java.util.List;

public class UserService implements IUserService, Injectable {
    private final UserDAO userDAO;

    public UserService() {

        this.userDAO = DefaultInjector.getService(UserDAO.class);
    }

    @Override
    public boolean checkValidUser(String username, String password){
        UserModel user = userDAO.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public List<UserModel> getAllUsers(){
        return userDAO.findALl();
    }
    @Override
    public void addUser(UserModel user){
        userDAO.save(user);
    }
    @Override
    public void deleteUser(UserModel user){
        userDAO.delete(user);
    }
}
