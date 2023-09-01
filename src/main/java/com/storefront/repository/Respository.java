package com.storefront.repository;

import com.storefront.dto.Item;
import com.storefront.dto.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Respository {
    //Items
    public static List<Item> items = new ArrayList<>();

    //Item indexing
    public static Map<Long, Item> itemMap = new HashMap<>();

    static {
        items.add(Item.builder()
                .id(1L)
                .price(3492.2)
                .description("Description1").pictureLocation("src/main/resources/static/img.png").build());
        items.add( Item.builder()
                .id(2L)
                .price(3492.2)
                .description("Description1").pictureLocation("src/main/resources/static/img.png").build());
        items.add(Item.builder()
                .id(3L)
                .price(3492.2)
                .description("Description1").pictureLocation("src/main/resources/static/img.png").build());
        items.add(Item.builder()
                .id(4L)
                .price(3492.2)
                .description("Description1").pictureLocation("src/main/resources/static/img.png").build());
        items.add(Item.builder()
                .id(5L)
                .price(3492.2)
                .description("Description1").pictureLocation("src/main/resources/static/img.png").build());

        //indexing
        items.forEach(item -> itemMap.put(item.getId(), item));
    }

    public static Map<String, Order> orderMap = new HashMap<>();

}
