package com.pedro.restapi.domain;

import jakarta.persistence.*;


@Entity
@Table(name = "department")
public class Department {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(name = "title")
    private String title;

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
}
