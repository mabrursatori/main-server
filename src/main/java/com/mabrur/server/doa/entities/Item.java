package com.mabrur.server.doa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "items_doa")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, nullable = false, unique = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contentIndo;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contentArabic;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @ManyToOne
    // @JsonBackReference
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentIndo() {
        return contentIndo;
    }

    public void setContentIndo(String contentIndo) {
        this.contentIndo = contentIndo;
    }

    public String getContentArabic() {
        return contentArabic;
    }

    public void setContentArabic(String contentArabic) {
        this.contentArabic = contentArabic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
