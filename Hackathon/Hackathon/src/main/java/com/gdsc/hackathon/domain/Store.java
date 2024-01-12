package com.gdsc.hackathon.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Store {
    @Id
    @GeneratedValue

    @Column(name = "STORE_ID", nullable = false)
    private Long id;

    @Column(name = "STORE_NAME", nullable = false)
    private String name;

    @Column(name = "STORE_DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "STORE_IMAGEURL")
    private String imageUrl;

    @Column(name = "STORE_OPERATOINGDAYS")
    private String operatingDays;

    @Column(name = "STORE_OPERATIONGSTATUS")
    private boolean operatingStatus;

    @Column(name = "STORE_CATEGORY")
    private String category;

    // Menu 엔티티를 참조하는 필드, 하나의 가게가 여러 개의 메뉴를 가질 수 있음.
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Menu> menus = new ArrayList<>();

}
