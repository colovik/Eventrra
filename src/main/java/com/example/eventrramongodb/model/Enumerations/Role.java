package com.example.eventrramongodb.model.Enumerations;

public enum Role {

    ROLE_USER("user"),
    ROLE_BAND("band"),

    ROLE_CLIENT("client"),

    ROLE_ADMIN("admin"),
    ROLE_PHOTOGRAPHER("photographer"),
    ROLE_CATERING("catering");

    private String name;

    Role(String s) {
        this.name = s;
    }
}

