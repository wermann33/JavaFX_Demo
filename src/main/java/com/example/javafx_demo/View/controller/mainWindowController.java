package com.example.javafx_demo.View.controller;

import com.example.javafx_demo.DefaultInjector;
import com.example.javafx_demo.MainApp;
import com.example.javafx_demo.View.viewmodels.LoginViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class mainWindowController {
    MainApp mainApp;
    private final LoginViewModel loginViewModel;
    public mainWindowController() {
        this.mainApp = DefaultInjector.getService(MainApp.class);
        this.loginViewModel = DefaultInjector.getService(LoginViewModel.class);
    }

    @FXML
    private Button logInButton;

    @FXML
    private PasswordField pwd;

    @FXML
    private TextField username;

    @FXML
    private Label warningLabel;

    @FXML
    void userLogIn(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {
        if(loginViewModel.checkLogin(username.getText(), pwd.getText())){
            warningLabel.setText("Success!");
            mainApp.changeScene("afterLogin.fxml");
        } else if (username.getText().isEmpty() || pwd.getText().isEmpty()) {
            warningLabel.setText("Please enter your data!");
        }
        else{
            warningLabel.setText("Wrong username or password");
        }
    }
}
