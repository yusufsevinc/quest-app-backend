package com.app.quesapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    private Long id;

    private String userName;
    private String password;


}
