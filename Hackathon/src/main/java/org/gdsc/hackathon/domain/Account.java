package org.gdsc.hackathon.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class Account {
    @Id
    @Column(name = "ACCOUNT_ID")
    private Long id;

    @Column(name = "ACCOUNT_NUMBER")
    private String account_number;

    @


}
