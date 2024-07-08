package ru.Amet_Kurtumerov.tgBot.entity;

import jakarta.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false,length = 50,unique = true)
    private String name;

    @ManyToOne
    private Category category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category parentCategory) {
        this.category = parentCategory;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
