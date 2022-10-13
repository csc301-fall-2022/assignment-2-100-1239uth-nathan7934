package com.example.backend.item_in_cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemInCartRepository extends JpaRepository<ItemInCart, Long> {
}
