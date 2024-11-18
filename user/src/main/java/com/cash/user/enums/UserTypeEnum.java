package com.cash.user.enums;

public enum UserTypeEnum {

    INDIVIDUAL("Individual"),
    CORPORATE("Corporate");

    private final String name;

    UserTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
