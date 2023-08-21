package com.mailapp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
public enum PostalType {

    LETTER("Письмо"),
    PACKAGE("Посылка"),
    PARCEL("Бандероль"),
    POSTCARD("Бандероль");

    private final String typeTitle;

    PostalType(String typeTitle) {
        this.typeTitle = typeTitle;
    }

    @Override
    public String toString() {
        return typeTitle;
    }
}
