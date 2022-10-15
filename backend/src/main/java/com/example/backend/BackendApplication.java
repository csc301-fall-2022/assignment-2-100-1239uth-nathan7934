package com.example.backend;

import com.example.backend.cart.Cart;
import com.example.backend.cart.CartRepository;
import com.example.backend.category.Category;
import com.example.backend.category.CategoryRepository;
import com.example.backend.discount.Discount;
import com.example.backend.discount.DiscountRepository;
import com.example.backend.item_in_cart.ItemInCart;
import com.example.backend.items.Item;
import com.example.backend.items.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CartRepository cartRepository, ItemRepository itemRepository, CategoryRepository categoryRepository, DiscountRepository discountRepository) {
        return args -> {
            Discount discount1 = new Discount("ABC050", 50);
            Discount discount2 = new Discount("ABC010", 10);
            Discount discount3 = new Discount("ABC100", 100);
            List<Discount> discounts = new ArrayList<>();
            discounts.add(discount1);
            discounts.add(discount2);
            discounts.add(discount3);
            discountRepository.saveAll(discounts);

            Category category = categoryRepository.save(new Category("clothing", new ArrayList<>()));
            Item item1 = new Item("hat", "beanie", 9.99f, "https://previews.123rf.com/images/jemastock/jemastock1802/jemastock180203579/95517489-beanie-winter-hat-icon-vector-illustration-graphic-design.jpg", category);
            List<Item> items = new ArrayList<>();
            items.add(item1);
            itemRepository.saveAll(items);


            Item item3 = new Item("shirt", "t-shirt", 19.99f, "https://www.pngitem.com/pimgs/m/172-1722226_t-shirt-purple-clip-art-blue-shirt-clipart.png", category);
            Item item2 = new Item("pant", "jeans", 44.99f, "https://media.istockphoto.com/vectors/pants-cartoon-vector-id1076492576?k=20&m=1076492576&s=612x612&w=0&h=905nrZDJOI6SIVT_9nG3VbvE_vuZXBXUv5luZOXOyxU=", category);
            List<ItemInCart> itemInCartList = new ArrayList<>();
            itemInCartList.add(new ItemInCart(1, item3));
            itemInCartList.add(new ItemInCart(3, item2));
            Cart cart1 = new Cart(itemInCartList, "Bob");

            cartRepository.save(cart1);
        };
    }
}