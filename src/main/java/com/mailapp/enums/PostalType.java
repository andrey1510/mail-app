package com.mailapp.enums;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum PostalType {

    LETTER("Письмо"),
    PACKAGE("Посылка"),
    PARCEL("Бандероль"),
    POSTCARD("Бандероль");

    private final String postalType;

}
