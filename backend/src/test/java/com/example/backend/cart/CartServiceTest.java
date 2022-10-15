package com.example.backend.cart;

import com.example.backend.discount.DiscountRepository;
import com.example.backend.item_in_cart.ItemInCartRepository;
import com.example.backend.items.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartServiceTest {

    @Autowired
    private CartService cartService;

    @MockBean
    private CartRepository cartRepository;

    @MockBean
    private ItemRepository itemRepository;

    @MockBean
    private ItemInCartRepository itemInCartRepository;

    @MockBean
    private DiscountRepository discountRepository;

    @BeforeEach
    void setUp() {
        cartRepository = Mockito.mock(CartRepository.class);
        cartService = new CartService(cartRepository, itemRepository, itemInCartRepository, discountRepository);

        Cart cart = new Cart();
        cart.setId(1L);
        Mockito.when(cartRepository.save(new Cart())).thenReturn(cart);
    }

    @Test
    void createCart() {
        Cart actualCart = cartService.createCart();
        assertEquals(1L, actualCart.getId());
    }
}