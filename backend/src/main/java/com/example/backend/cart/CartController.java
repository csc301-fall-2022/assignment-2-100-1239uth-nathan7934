package com.example.backend.cart;

import com.example.backend.exceptions.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

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

    @PatchMapping("{id}")
    public ResponseEntity<Cart> getCartWithDiscount(@PathVariable Long id, @RequestParam(name = "code") String discountCode) throws NotFoundException {
        if (discountCode.length() != 6)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(cartService.getCartWithDiscount(id, discountCode), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cart> addCart(@RequestBody(required = false) Cart cart) throws URISyntaxException {
        Cart newCart = cart == null ? cartService.createCart() : cartService.createCart(cart);

        HttpHeaders headers = new HttpHeaders();
        if (cart != null)
            headers.setLocation(new URI("/api/cart/" + cart.getId()));
        return new ResponseEntity<>(
                newCart,
                headers,
                HttpStatus.CREATED
        );
    }

    @PostMapping("{cartId}/item/{itemId}")
    public ResponseEntity<Cart> addItemToCart(@PathVariable Long cartId, @PathVariable Long itemId) throws NotFoundException {
        return new ResponseEntity<>(cartService.addItemToCart(cartId, itemId), HttpStatus.OK);
    }

    @PatchMapping("{cartId}/item/{itemId}")
    public ResponseEntity<Cart> updateItemQuantityFromCart(@PathVariable Long cartId, @PathVariable Long itemId, @RequestParam int quantity) throws NotFoundException {
        return new ResponseEntity<>(cartService.updateQuantityInItem(cartId, itemId, quantity), HttpStatus.OK);
    }

    @DeleteMapping("{cartId}/item/{itemId}")
    public ResponseEntity<Cart> deleteItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) throws NotFoundException {
        return new ResponseEntity<>(cartService.removeItemFromCart(cartId, itemId), HttpStatus.OK);
    }

    @DeleteMapping("{cartId}")
    public ResponseEntity<Cart> deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
