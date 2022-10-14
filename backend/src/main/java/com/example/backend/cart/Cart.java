package com.example.backend.cart;

import com.example.backend.item_in_cart.ItemInCart;
import com.example.backend.items.Item;
import com.example.backend.user.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Cart")
@Table(name = "carts")
public class Cart {
    @Id
    @SequenceGenerator(name = "cart_sequence", sequenceName = "cart_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "cart_sequence")
    @Column(name = "cart_id")
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private List<ItemInCart> itemsInCart = new ArrayList<>();
    @Transient
    private Integer length;

    @Transient
    private Float totalCost;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Cart(List<ItemInCart> itemsInCart, String userName) {
        this.itemsInCart = itemsInCart;
        this.user = null;
    }

    public Cart() {
    }

    public Float getTotalCost() {
        float totalCost = 0;
        for (ItemInCart item : itemsInCart) {
            totalCost += item.getPrice();
        }
        return totalCost;
    }

    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
    }

    public void addItem(Item item) {
        for (ItemInCart itemInCart : this.itemsInCart) {
            if (itemInCart.getItem().getId().equals(item.getId())) {
                itemInCart.incrementQuantity();
                return;
            }
        }
        this.itemsInCart.add(new ItemInCart(1, item));
    }

    public void removeItem(ItemInCart itemInCart) {
        this.itemsInCart.remove(itemInCart);
    }

    public void updateItemQuantity(ItemInCart item, int newQuantity) {
        this.itemsInCart.get(itemsInCart.indexOf(item)).setQuantity(newQuantity);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", itemsInCart=" + itemsInCart +
                ", length=" + length +
                ", totalCost=" + totalCost +
                ", user=" + user +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemInCart> getItemsInCart() {
        return itemsInCart;
    }

    public void setItemsInCart(List<ItemInCart> itemsInCart) {
        this.itemsInCart = itemsInCart;
    }

    public Integer getLength() {
        return itemsInCart.size();
    }

    public void setLength(Integer length) {
        this.length = length;
    }

}