package com.cauanengdev.pamydex.enums;

public interface Convertable<T> {
    String getDescription();
    T fromDescription(String description);
}
