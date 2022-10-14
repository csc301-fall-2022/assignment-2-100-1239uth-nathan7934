package com.example.backend.items;

import com.example.backend.category.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Item")
@Table(name = "items")
public class Item {
    @Id
    @SequenceGenerator(name = "item_sequence", sequenceName = "item_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "item_sequence")
    @Column(name = "item_id")
    private Long id;
    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "price", nullable = false)
    private Float price;
    @Column(name = "pic_url", columnDefinition = "TEXT")
    private String pictureUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @JsonIgnore
    private Category category;

    @Transient
    private String categoryName;

    public String getCategoryName() {
        return this.category.getName();
    }

    public Item() {
    }

    public Item(String name, String description, Float price, String pictureUrl, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.pictureUrl = pictureUrl;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", pictureUrl='" + pictureUrl + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
