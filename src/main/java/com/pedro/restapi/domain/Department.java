package com.pedro.restapi.domain;

import jakarta.persistence.*;
import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
public class Department{


    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
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
