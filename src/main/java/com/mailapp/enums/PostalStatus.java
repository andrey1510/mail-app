package com.mailapp.enums;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum PostalStatus {

    REGISTERED("Зарегистрировано"),
    IN_OFFICE("Прибыло в почтовое отделение"),
    OUT_OF_OFFICE("Покинуло почтовое отделение"),
    RECEIVED("Получено адресатом");

    private final String postalStatus;

}
