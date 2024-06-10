package com.example.javafx_demo.BL;
import com.example.javafx_demo.DAL.repositories.UserDAO;

import com.example.javafx_demo.BL.models.UserModel;
import com.example.javafx_demo.DefaultInjector;
import com.example.javafx_demo.Injectable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserService implements IUserService, Injectable {
    private final UserDAO userDAO;
    private final Logger logger;


    public UserService() {
        logger = LoggerFactory.getLogger(UserService.class);
        this.userDAO = DefaultInjector.getService(UserDAO.class);
        logger.info("UserService initialized");
    }

    @Override
    public boolean checkValidUser(String username, String password){
        UserModel user = userDAO.findByUsername(username);
        boolean isValid = user != null && user.getPassword().equals(password);
        logger.debug("Checking user validity: username={}, isValid={}", username, isValid);
        return isValid;
    }

    @Override
    public List<UserModel> getAllUsers(){
        List<UserModel> users = userDAO.findALl();
        logger.info("Retrieved all users, count={}", users.size());
        return users;
    }
    @Override
    public void addUser(UserModel user){
        userDAO.save(user);
        logger.info("Added new user: username={}", user.getUsername());
    }
    @Override
    public void deleteUser(UserModel user){
        userDAO.delete(user);
        logger.info("Deleted user: username={}", user.getUsername());
    }
}
