package com.example.backend.items;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item addNewItem(Item item) {
        return itemRepository.save(item);
    }

    public Optional<Item> getByName(String name) {
        return itemRepository.findByName(name);
    }
}
