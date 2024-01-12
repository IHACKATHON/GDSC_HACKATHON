package com.gdsc.hackathon.service;

import com.gdsc.hackathon.domain.Store;
import com.gdsc.hackathon.dto.StoreDto;

import java.util.List;

public interface StoreService {
    Store createStore(Store store);
    List<Store> searchStores(String name);
    StoreDto getStore(Long id);
    Store findStoreById(Long id);
}
