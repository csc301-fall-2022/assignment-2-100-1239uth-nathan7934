package com.example.backend.category;

import com.example.backend.items.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @SequenceGenerator(name = "category_sequence", sequenceName = "category_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "category_sequence")
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_name", unique = true, nullable = false)
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Item> items;

    public Category() {
    }

    public Category(String name, List<Item> items) {
        this.name = name;
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
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
}
