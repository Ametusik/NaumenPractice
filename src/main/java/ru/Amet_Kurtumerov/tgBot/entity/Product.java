package ru.Amet_Kurtumerov.tgBot.entity;

import jakarta.persistence.*;
import jdk.jfr.Name;

@Entity
public class Product {


    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Category category;

    @Column(nullable = false,length = 50,unique = true)
    private String name;

    @Column(nullable = false,length = 400,unique = true)
    private String description;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(precision = 15, scale = 2, nullable = false)
    private Double price;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
