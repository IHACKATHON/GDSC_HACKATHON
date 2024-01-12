package com.gdsc.hackathon.controller;

import com.gdsc.hackathon.domain.Store;
import com.gdsc.hackathon.dto.StoreDto;
import com.gdsc.hackathon.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") //아래의 코드들 앞에 /api가 붙는다.
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/store")
    public Store createStore(@RequestBody Store store) {
        return storeService.createStore(store);
    }

    // 가게 검색 요청을 처리하는 컨트롤러
    @GetMapping("/store/search")
    public List<Store> searchStores(@RequestParam String name) {
        return storeService.searchStores(name);
    }
    @GetMapping("/store/{id}")
    public Store getStore(@PathVariable Long id) {
        return storeService.findStoreById(id);
    }

}
