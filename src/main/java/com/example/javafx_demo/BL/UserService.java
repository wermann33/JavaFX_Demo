package com.example.javafx_demo.BL;
import com.example.javafx_demo.DAL.repositories.UserDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.example.javafx_demo.DAL.HibernateUtil;

import com.example.javafx_demo.BL.models.UserModel;

import java.util.List;

public class UserService {
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public boolean checkValidUser(String username, String password){
        UserModel user = userDAO.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    public List<UserModel> getAllUsers(){
        return userDAO.findALl();
    }

    public void addUser(UserModel user){
        userDAO.save(user);
    }

    public void deleteUser(UserModel user){
        userDAO.delete(user);
    }
}
