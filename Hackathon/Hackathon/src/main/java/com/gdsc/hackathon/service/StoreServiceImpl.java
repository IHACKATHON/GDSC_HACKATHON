package com.gdsc.hackathon.service;

import com.gdsc.hackathon.domain.Store;
import com.gdsc.hackathon.dto.MenuDto;
import com.gdsc.hackathon.dto.StoreDto;
import com.gdsc.hackathon.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public List<Store> searchStores(String name) {
        return storeRepository.findByNameContaining(name);
    }

    @Override
    @Transactional
    public StoreDto getStore(Long id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No Store with id: " + id));

        StoreDto storeDto = new StoreDto(); // Store를 StoreDto로 변환
        // StoreDto의 필드를 채워줍니다...

        List<MenuDto> menuDtos = store.getMenus().stream()
                .map(menu -> {
                    MenuDto menuDto = new MenuDto(); // Menu를 MenuDto로 변환
                    // MenuDto의 필드를 채움
                    return menuDto;
                })
                .collect(Collectors.toList());

        storeDto.setMenus(menuDtos); // StoreDto에 메뉴 목록 추가

        return storeDto;
    }

    @Override
    public Store findStoreById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }
}
