module com.example.javafx_demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires lombok;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;
    requires kernel;
    requires layout;
    requires io;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;


    opens com.example.javafx_demo to javafx.fxml, javafx.graphics, org.apache.logging.log4j;
    exports com.example.javafx_demo;
    exports com.example.javafx_demo.View.controller;
    exports com.example.javafx_demo.BL;
    exports com.example.javafx_demo.View.viewmodels;
    opens com.example.javafx_demo.View.controller to javafx.fxml, javafx.graphics;
    opens com.example.javafx_demo.BL to javafx.fxml, javafx.graphics;
    exports com.example.javafx_demo.BL.models;
    opens com.example.javafx_demo.BL.models to javafx.fxml, javafx.graphics, org.hibernate.orm.core;
    exports com.example.javafx_demo.DAL;
    opens com.example.javafx_demo.DAL to javafx.fxml, javafx.graphics;
    opens com.example.javafx_demo.View.viewmodels to javafx.fxml, javafx.graphics;
}