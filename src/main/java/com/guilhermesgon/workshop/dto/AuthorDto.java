package com.guilhermesgon.workshop.dto;

import com.guilhermesgon.workshop.domain.User;

public class AuthorDto {

    private String id;
    private String name;

    public AuthorDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }

    public AuthorDto() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}


