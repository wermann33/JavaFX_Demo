package com.example.javafx_demo.View.controller;

import com.example.javafx_demo.Services.IPDFService;
import com.example.javafx_demo.Services.PDFService;
import com.example.javafx_demo.BL.models.UserModel;
import com.example.javafx_demo.DefaultInjector;
import com.example.javafx_demo.MainApp;
import com.example.javafx_demo.View.viewmodels.AfterLoginViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AfterLoginController implements Initializable {

    @FXML
    public Button generatePDFButton;
    @FXML
    private TableView<UserModel> userTable;
    @FXML
    private TableColumn<UserModel, String> usernameColumn;
    @FXML
    private TableColumn<UserModel, String> passwordColumn;
    private final MainApp mainApp;
    private final AfterLoginViewModel afterLoginViewModel;
    private final IPDFService pdfService;

    @FXML
    private Button addButton;

    @FXML
    private Button closeButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button logOutButton;

    @FXML
    public void generatePDF(ActionEvent actionEvent) {
        try{
            pdfService.createUserListPDF("UserList.pdf");
            System.out.println("PDF generated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public AfterLoginController() {
        this.mainApp = DefaultInjector.getService(MainApp.class);
        this.afterLoginViewModel = DefaultInjector.getService(AfterLoginViewModel.class);
        this.pdfService = DefaultInjector.getService(PDFService.class);
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        mainApp.changeScene("mainWindow.fxml");
    }

    @FXML
    void closeApp(ActionEvent event) throws IOException {
        MainApp.getStg().close();
    }

    @FXML
    public void addUser(ActionEvent actionEvent) {
        TextInputDialog usernameDialog = new TextInputDialog();
        usernameDialog.setTitle("Add User");
        usernameDialog.setHeaderText("Enter new user details");
        usernameDialog.setContentText("Username:");

        Optional<String> username = Optional.empty();
        while (username.isEmpty() || username.get().length() < 3) {
            username = usernameDialog.showAndWait();
            if (username.isEmpty()) {
                return;  // Abbrechen, wenn der Benutzer den Dialog schließt oder auf "Abbrechen" klickt
            }
            if (username.get().length() < 3) {
                usernameDialog.setHeaderText("Username must be at least 3 characters long. Please try again:");
            }
        }

        TextInputDialog passwordDialog = new TextInputDialog();
        passwordDialog.setTitle("Add User");
        passwordDialog.setHeaderText("Enter new user details");
        passwordDialog.setContentText("Password:");

        Optional<String> password = Optional.empty();
        while (password.isEmpty() || password.get().length() < 3) {
            password = passwordDialog.showAndWait();
            if (password.isEmpty()) {
                return;  // Abbrechen, wenn der Benutzer den Dialog schließt oder auf "Abbrechen" klickt
            }
            if (password.get().length() < 3) {
                passwordDialog.setHeaderText("Password must be at least 3 characters long. Please try again:");
            }
        }

        UserModel newUser = new UserModel();
        newUser.setUsername(username.get());
        newUser.setPassword(password.get());
        afterLoginViewModel.addUser(newUser);
    }

    @FXML
    public void deleteUser(ActionEvent actionEvent) {
        UserModel selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            afterLoginViewModel.deleteUser(selectedUser);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        userTable.setItems(afterLoginViewModel.getUserTableList());
    }


}
