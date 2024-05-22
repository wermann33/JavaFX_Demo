package com.example.javafx_demo;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class MainWindowTest extends ApplicationTest {
    @Override
    public void start(Stage stage) throws Exception {
        new MainApp().start(stage);
    }

    @BeforeEach
    void setUp() throws Exception {
        FxToolkit.setupStage(Stage::show);
    }

    @AfterEach
    void tearDown() throws Exception {
        FxToolkit.cleanupStages();
    }

    @Test
    void testLoginButtonEnabled() {
        verifyThat("#logInButton", isEnabled());
    }

    @Test
    void testLoginWithValidCredentials() {
        clickOn("#username").write("wermann");
        clickOn("#pwd").write("123");
        clickOn("#logInButton");

        verifyThat("#logOutButton", isVisible());
        verifyThat("#closeButton", isVisible());
    }

    @Test
    void testLogoutButtonLogsOutUser() {
        testLoginWithValidCredentials();

        clickOn("#logOutButton");
        verifyThat("#logInButton", isVisible());
    }

    @Test
    void testLoginWithInvalidCredentials() {
        clickOn("#username").write("invalid");
        clickOn("#pwd").write("invalid");
        clickOn("#logInButton");

        verifyThat("#warningLabel", hasText("Wrong username or password"));
    }
}