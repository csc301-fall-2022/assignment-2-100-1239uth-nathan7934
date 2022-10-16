package com.example.backend.items;

import com.example.backend.category.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = {"job.autorun.enabled=false"})
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @MockBean
    private ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        itemService = new ItemService(itemRepository);
        Category category = new Category("clothing", new ArrayList<>());
        Item item3 = new Item("shirt", "t-shirt", 19.99f, "https://www.pngitem.com/pimgs/m/172-1722226_t-shirt-purple-clip-art-blue-shirt-clipart.png", category);

        Mockito.when(itemRepository.findByName("shirt")).thenReturn(Optional.of(item3));
    }

    @Test
    void getAllItems() {
        assertEquals("shirt", itemService.getByName("shirt").get().getName());
    }
}