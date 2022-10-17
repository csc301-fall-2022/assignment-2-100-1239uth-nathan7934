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
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    @ConditionalOnProperty(prefix = "job.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
    CommandLineRunner commandLineRunner(CartRepository cartRepository, ItemRepository itemRepository, CategoryRepository categoryRepository, DiscountRepository discountRepository) {
        return args -> {
            Discount discount1 = new Discount("ABC050", 50);
            Discount discount2 = new Discount("ABC010", 10);
            Discount discount3 = new Discount("ABC100", 100);
            discountRepository.saveAll(List.of(discount1, discount2, discount3));

            Category clothing = categoryRepository.save(new Category("clothing", new ArrayList<>()));
            Category food = categoryRepository.save(new Category("food", new ArrayList<>()));
            Category technology = categoryRepository.save(new Category("technology", new ArrayList<>()));

            Item hat = new Item("hat", "beanie", 9.99f, "https://previews.123rf.com/images/jemastock/jemastock1802/jemastock180203579/95517489-beanie-winter-hat-icon-vector-illustration-graphic-design.jpg", clothing);
            Item pant = new Item("pant", "jeans", 44.99f, "https://media.istockphoto.com/vectors/pants-cartoon-vector-id1076492576?k=20&m=1076492576&s=612x612&w=0&h=905nrZDJOI6SIVT_9nG3VbvE_vuZXBXUv5luZOXOyxU=", clothing);
            Item shirt = new Item("shirt", "t-shirt", 19.99f, "https://www.pngitem.com/pimgs/m/172-1722226_t-shirt-purple-clip-art-blue-shirt-clipart.png", clothing);
            Item shoes = new Item("shoes", "adidas shoes", 79.99f, "https://toppng.com/uploads/preview/adidas-shoes-clipart-adidas-sneaker-adidas-gifs-11563558879xa1g81njf5.png", clothing);
            Item uoft = new Item("UofT Merch", "UofT bookstore hoodie", 49.99f, "https://www.uoftbookstore.com/Product%20Images/14927423_media-00.default.jpg?resizeid=3&resizeh=600&resizew=600", clothing);

            Item banana = new Item("banana", "ripe bananas", 2.99f, "https://creazilla-store.fra1.digitaloceanspaces.com/cliparts/4498/bananas-clipart-xl.png", food);
            Item apple = new Item("apple", "red apples", 3.99f, "https://clipart.world/wp-content/uploads/2020/06/apple-clipart.jpg", food);
            Item oranges = new Item("orange", "orange oranges", 3.99f, "http://www.mayrand.ca/globalassets/mayrand/catalog-mayrand/fruit-et-legume/07822-orange--navel.jpg", food);
            Item mango = new Item("mango", "sweet mangos", 4.99f, "https://media.istockphoto.com/vectors/mango-vector-id162494596?k=20&m=162494596&s=612x612&w=0&h=Aaggx_bLi9vPh8P1ore-w4BqA6lPE1n4Fn4kuwzji_c=", food);

            Item macbook = new Item("Macbook Pro", "M1 Macbook Pro 13 inch", 1499.99f, "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/mbp14-spacegray-select-202110?wid=904&hei=840&fmt=jpeg&qlt=90&.v=1632788573000", technology);
            Item iPad = new Item("iPad Pro", "iPad Pro 12.9 inch", 999.99f, "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/ipad-pro-12-11-select-202104_FMT_WHH?wid=2000&hei=2000&fmt=jpeg&qlt=90&.v=1617067383000", technology);
            Item iPhone = new Item("iPhone 14 pro max", "iPhone 14 pro max 128GB Silver", 1529.99f, "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-14-pro-finish-select-202209-6-7inch-silver?wid=5120&hei=2880&fmt=jpeg&qlt=90&.v=1663703841892", technology);
            Item appleWatch = new Item("Apple Watch 8", "Apple Watch series 8 45mm", 529.99f, "https://www.apple.com/v/apple-watch-series-8/a/images/meta/gps-lte__de35dernyje6_og.png", technology);
            Item pixelWatch = new Item("Pixel Watch", "Google Pixel Watch 1", 449.99f, "https://www.fitbit.com/global/content/dam/fitbit/global/pdp/devices/google-pixel-watch/hero-static/charcoal/google-pixel-watch-charcoal-device-3qt-left.png", technology);
            Item samsungWatch = new Item("Galaxy Watch 5", "Samsung Galaxy Watch 5", 399.99f, "https://stg-images.samsung.com/is/image/samsung/p6pim/global/wearable/2208/global-wearable-SM-R905_Dynamic_Sliver_Sport_Band_Purple-533107715?$720_720_JPG$", technology);



            itemRepository.saveAll(List.of(hat, pant, shirt, shoes, uoft, banana, apple, oranges, mango, macbook, iPad, iPhone, appleWatch, pixelWatch, samsungWatch));

        };
    }
}