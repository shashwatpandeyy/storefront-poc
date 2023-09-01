package com.storefront.service;

import com.storefront.dto.Item;
import com.storefront.repository.Respository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ItemService {

    public List<Item> getItems() {
        return Respository.items;
    }
    
    public int getItemSize() {
        return Respository.items.size();
    }

    public Optional<Item> getItemById(Long itemId) {
        return Optional.ofNullable(Respository.itemMap.get(itemId));
    }
}
