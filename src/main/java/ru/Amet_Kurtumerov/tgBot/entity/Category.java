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
    @JoinColumn
    private Category parentCategory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
