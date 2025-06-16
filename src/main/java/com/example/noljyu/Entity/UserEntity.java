package com.example.noljyu.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "\"noluser\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @Column(name = "\"id\"")
    private String id;

    @Column(name = "\"pw\"")
    private String pw;

    @Column(name = "\"name\"")
    private String name;

    @Column(name = "\"address\"")
    private String address;

    @Column(name = "\"phone\"")
    private String phone;

    @Column(name = "\"nickname\"")
    private String nickname;

    @Column(name = "\"myanimal\"")
    private String myanimal;

    @Column(name = "\"admin\"")
    private String admin;

    @Column(name = "\"petname\"")
    private String petname;

    @Column(name = "\"photo\"")
    private String photo;
}

