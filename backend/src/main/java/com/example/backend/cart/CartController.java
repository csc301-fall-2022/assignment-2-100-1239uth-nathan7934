package com.example.backend.cart;

import com.example.backend.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Cart> getCart(@PathVariable Long id) {
        return this.cartService.getCart(id)
                .map(cart -> new ResponseEntity<>(cart, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Cart> addCart(@RequestBody(required = false) Cart cart) {
        return new ResponseEntity<>(
                cart == null ? cartService.createCart() : cartService.createCart(cart),
                HttpStatus.CREATED
        );
    }

    @PostMapping("{cartId}/item/{itemId}")
    public ResponseEntity<Cart> addItemToCart(@PathVariable Long cartId, @PathVariable Long itemId) throws NotFoundException {
        return new ResponseEntity<>(cartService.addItemToCart(cartId, itemId), HttpStatus.OK);
    }

    @PutMapping("{cartId}/item/{itemId}")
    public ResponseEntity<Cart> updateItemQuantityFromCart(@PathVariable Long cartId, @PathVariable Long itemId, @RequestBody Quantity quantity) throws NotFoundException {
        return new ResponseEntity<>(cartService.updateQuantityInItem(cartId, itemId, quantity.quantity()), HttpStatus.OK);
    }

    @DeleteMapping("{cartId}/item/{itemId}")
    public ResponseEntity<Cart> deleteItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) throws NotFoundException {
        return new ResponseEntity<>(cartService.removeItemFromCart(cartId, itemId), HttpStatus.OK);
    }

    /**
     * RequestBody object to receive a quantity from HTTP Request
     *
     * @param quantity integer representing quantity of an item in cart
     */
    private record Quantity(int quantity) {
    }
}
