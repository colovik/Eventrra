package com.example.model.Enumerations;

public enum Status {
    CREATED("Created"),
    APPROVED("Approved"),
    REJECTED("Rejected"),
    PROCESSED("Processed");
    private String name;

    Status(String status) {
        this.name = status;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return name;
    }
}
