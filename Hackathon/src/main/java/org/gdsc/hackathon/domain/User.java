package org.gdsc.hackathon.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;
    @Column(name = "USER_NAME")
    private String name;
    @Column(name = "USER_EMAIL")
    private String email;
    @Column(name = "USER_ISBUISNESS")
    private String isBuisness;
    @Column(name = "USER_TEL")
    private String tel;
}
