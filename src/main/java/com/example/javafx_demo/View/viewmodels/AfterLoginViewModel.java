package com.example.javafx_demo.View.viewmodels;

import com.example.javafx_demo.BL.IUserService;
import com.example.javafx_demo.BL.UserService;
import com.example.javafx_demo.BL.models.UserModel;
import com.example.javafx_demo.DefaultInjector;
import com.example.javafx_demo.Injectable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;


public class AfterLoginViewModel implements Injectable {
    private final IUserService userService;

    @Getter
    private ObservableList<UserModel> userTableList;

    public AfterLoginViewModel() {
        this.userService = DefaultInjector.getService(UserService.class);
        this.userTableList = FXCollections.observableArrayList();
        loadUsers();
    }

    private void loadUsers() {
        userTableList.clear();
        var validUserModels = userService.getAllUsers();
        userTableList.addAll(validUserModels);
    }

    public void addUser(UserModel user) {
        userService.addUser(user);
        loadUsers();
    }

    public void deleteUser(UserModel user) {
        userService.deleteUser(user);
        loadUsers();
    }
}
