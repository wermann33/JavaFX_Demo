package com.example.javafx_demo.BL.models;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "usermodels")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
}
