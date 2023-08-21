package com.mailapp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@Getter
public enum PostalStatus {

    REGISTERED("Зарегистрировано"),
    IN_OFFICE("Прибыло в почтовое отделение"),
    OUT_OF_OFFICE("Покинуло почтовое отделение"),
    RECEIVED("Получено адресатом");


    PostalStatus(String statusTitle) {
        this.statusTitle = statusTitle;
    }

    private final String statusTitle;

    @Override
    public String toString() {
        return statusTitle;
    }
}
