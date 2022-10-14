package com.example.backend.cart;

import com.example.backend.exceptions.NotFoundException;
import com.example.backend.item_in_cart.ItemInCart;
import com.example.backend.item_in_cart.ItemInCartRepository;
import com.example.backend.items.Item;
import com.example.backend.items.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final ItemInCartRepository itemInCartRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ItemRepository itemRepository, ItemInCartRepository itemInCartRepository) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.itemInCartRepository = itemInCartRepository;
    }

    public Optional<Cart> getCart(Long id) {
        return cartRepository.findById(id);
    }

    public Cart createCart(Cart cart) {
        return this.cartRepository.save(cart);
    }

    public Cart createCart() {
        return this.cartRepository.save(new Cart());
    }

    @Transactional
    public Cart addItemToCart(Long cartId, Long itemId) throws NotFoundException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("The cart with id " + cartId + " was not found"));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("The item with id " + itemId + " was not found"));
        cart.addItem(item);

        return cart;
    }

    @Transactional
    public Cart removeItemFromCart(Long cartId, Long itemId) throws NotFoundException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("The cart with id " + cartId + " was not found"));
        ItemInCart itemInCart = itemInCartRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("The item in cart with id " + itemId + " was not found"));
        cart.removeItem(itemInCart);

        return cart;
    }

    @Transactional
    public Cart updateQuantityInItem(Long cartId, Long itemInCartId, int newQuantity) throws NotFoundException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("The cart with id " + cartId + " was not found"));
        ItemInCart item = itemInCartRepository.findById(itemInCartId)
                .orElseThrow(() -> new NotFoundException("The item in cart with id " + itemInCartId + " was not found"));
        cart.updateItemQuantity(item, newQuantity);

        return cart;
    }
}
