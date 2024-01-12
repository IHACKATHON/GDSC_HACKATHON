package org.gdsc.hackathon.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Store {
    @Id
    @GeneratedValue
    @Column(name = "STORE_ID")
    private Long id;

    @Column(name = "STORE_NAME")
    
}
