package com.example.backend.cart;

import com.example.backend.discount.Discount;
import com.example.backend.discount.DiscountRepository;
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

    private final DiscountRepository discountRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ItemRepository itemRepository, ItemInCartRepository itemInCartRepository, DiscountRepository discountRepository) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.itemInCartRepository = itemInCartRepository;
        this.discountRepository = discountRepository;
    }

    public Optional<Cart> getCart(Long id) {
        return cartRepository.findById(id);
    }

    @Transactional
    public Cart getCartWithDiscount(Long id, String code) throws NotFoundException {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("The cart with id " + id + " was not found"));

        Discount discount = discountRepository.findDiscountByCode(code)
                .orElseThrow(() -> new NotFoundException("The discount code " + code + " does not exist"));

        cart.setDiscount(discount.getDiscountAmount());

        return cart;
    }

    public Cart createCart(Cart cart) {
        return this.cartRepository.save(cart);
    }

    public Cart createCart() {
        return this.cartRepository.save(new Cart());
    }

    @Transactional
    public Cart addItemToCart(Long cartId, Long itemId) throws NotFoundException {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new NotFoundException("The cart with id " + cartId + " was not found"));
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
        if (!cart.updateItemQuantity(item, newQuantity))
            throw new NotFoundException("the item " + item.getId() + " in cart " + cart.getId() + " was not found");

        return cart;
    }

    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}
