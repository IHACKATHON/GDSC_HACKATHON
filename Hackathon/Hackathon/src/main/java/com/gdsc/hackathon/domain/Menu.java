package com.gdsc.hackathon.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_ID")
    private Long id;

    @Column(name = "MENU_NAME")
    private String name; // 메뉴명
    @Column(name = "MENU_PRICE")
    private int price; // 가격
    @Column(name = "MENU_IMAGEURL")
    private String imageUrl; //이미지

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store; // 어떤 가게의 메뉴인지 정보

    // equals, hashCode, toString 메소드 생략...
}
