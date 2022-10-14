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
        return cartService.getCart(id)
                .map(cart -> new ResponseEntity<>(cart, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    public ResponseEntity<Cart> getCartWithDiscount(@PathVariable Long id, @RequestBody Discount responseDiscount) throws NotFoundException {
        if (responseDiscount == null || (responseDiscount.discount() <= 0 || responseDiscount.discount() > 100))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(cartService.getCartWithDiscount(id, responseDiscount.discount()), HttpStatus.OK);
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
    public ResponseEntity<Cart> updateItemQuantityFromCart(@PathVariable Long cartId, @PathVariable Long itemId, @RequestBody Quantity responseQuantity) throws NotFoundException {
        return new ResponseEntity<>(cartService.updateQuantityInItem(cartId, itemId, responseQuantity.quantity()), HttpStatus.OK);
    }

    @DeleteMapping("{cartId}/item/{itemId}")
    public ResponseEntity<Cart> deleteItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) throws NotFoundException {
        return new ResponseEntity<>(cartService.removeItemFromCart(cartId, itemId), HttpStatus.OK);
    }

    /**
     * RequestBody objects to receive a value from HTTP Request.
     * <br/>
     * name of attribute represents the format for the input field the client has to use.
     */
    private record Quantity(int quantity) {
    }

    private record Discount(int discount) {
    }
}
