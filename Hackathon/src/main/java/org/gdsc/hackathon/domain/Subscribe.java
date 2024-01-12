package org.gdsc.hackathon.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class Subscribe {

    @Id
    @Column
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;


}
