package com.example.backend.item_in_cart;

import com.example.backend.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemInCartService {
    private final ItemInCartRepository repository;

    public ItemInCartService(ItemInCartRepository repository) {
        this.repository = repository;
    }

    public List<ItemInCart> getAllItemsInCart() {
        return repository.findAll();
    }

    public ItemInCart addItemToCart(ItemInCart item) {
        return repository.save(item);
    }

    @Transactional
    public ItemInCart updateItemQuantity(Long id, Integer quantity) throws NotFoundException {
        ItemInCart item = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item with id " + id + " not found."));

        if (quantity != null && quantity >= 0)
            item.setQuantity(quantity);

        return item;
    }
}
