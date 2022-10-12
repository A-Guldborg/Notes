package com.exam.chillhub.enums;

public enum MediaType {
    ANY("Any"),
    MOVIE("Movies"),
    SERIES("Series");

    private String name;

    MediaType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
