package com.gdsc.hackathon.dto;

import java.util.List;

public class StoreDto {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private String operatingDay;
    private boolean operatingStatus;
    private String category;
    private List<MenuDto> menus; // 메뉴 목록 필드 추가
    public void setMenus(List<MenuDto> menus) {
        this.menus = menus;
    }
}
