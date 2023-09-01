package com.storefront.controller;

import com.storefront.dto.Item;
import com.storefront.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<Item> getItems(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "3") int size) {
        int startIndex = page * size;
        int endIndex = Math.min(startIndex + size, itemService.getItemSize());
        return itemService.getItems().subList(startIndex, endIndex);
    }

    @GetMapping("/{itemId}/image")
    public ResponseEntity<?> downloadItemImage(@PathVariable Long itemId) throws IOException {
        Optional<Item> optionalItem = itemService.getItemById(itemId);
        byte[] imageBytes = null;
        if (optionalItem.isPresent()) {
            imageBytes = Files.readAllBytes(Path.of(optionalItem.get().getPictureLocation()));
            return ResponseEntity.ok()
                    .contentLength(imageBytes.length)
                    .contentType(MediaType.IMAGE_PNG)
                    .body(new ByteArrayResource(imageBytes));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image with requested id not found!");
        }
    }
}

