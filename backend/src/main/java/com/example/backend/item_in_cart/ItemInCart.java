package com.example.backend.item_in_cart;

import com.example.backend.items.Item;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "items_in_cart")
public class ItemInCart {
    @Id
    @SequenceGenerator(name = "item_in_cart_sequence", sequenceName = "item_in_cart_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "item_in_cart_sequence")
    @Column(name = "item_in_cart_id")
    private Long id;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "item_id")
    private Item item;
    @Transient
    // quantity * price
    private Float price;

    public ItemInCart() {
    }

    public ItemInCart(Integer quantity, Item item) {
        this.quantity = quantity;
        this.item = item;
    }

    @Override
    public String toString() {
        return "ItemInCart{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", item=" + item +
                ", price=" + price +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Float getPrice() {
        return this.quantity * this.item.getPrice();
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
