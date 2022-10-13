package com.example.backend.cart;

import com.example.backend.items.ItemInCart;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Cart")
@Table(name = "cart")
public class Cart {
    @Id
    @SequenceGenerator(name = "cart_sequence", sequenceName = "cart_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "cart_sequence")
    @Column(name = "cart_id")
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private List<ItemInCart> itemsInCart;
    @Transient
    private Integer length;

//    @OneToOne(mappedBy = "cart")
//    private User user;

    public Cart(List<ItemInCart> itemsInCart, Integer length) {
        this.itemsInCart = itemsInCart;
        this.length = length;
//        this.user = user;
    }

    public Cart() {
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + id +
                ", itemsInCart=" + itemsInCart +
                ", length=" + length +
//                ", user=" + user +
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
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @PostLoad
    public void postLoad() {
        this.length = itemsInCart.size();
    }
}
