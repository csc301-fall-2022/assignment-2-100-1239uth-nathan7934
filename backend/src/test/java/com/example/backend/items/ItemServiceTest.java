package com.example.backend.items;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @MockBean
    private ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        itemService = new ItemService(itemRepository);

        Mockito.when(itemRepository.findAll()).thenReturn(new ArrayList<>());
    }

    @Test
    void getAllItems() {
        assertEquals(0, itemService.getAllItems().size());
    }
}