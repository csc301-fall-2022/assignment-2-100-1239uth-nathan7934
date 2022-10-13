package com.example.backend.item_in_cart;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("cart/items")
public class ItemInCartController {
    private final ItemInCartService service;

    public ItemInCartController(ItemInCartService service) {
        this.service = service;
    }

}
