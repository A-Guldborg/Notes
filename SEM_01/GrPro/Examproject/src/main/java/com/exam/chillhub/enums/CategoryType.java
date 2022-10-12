package com.exam.chillhub.enums;

public enum CategoryType {
    NONE("No category"),
    CRIME("Crime"),
    DRAMA("Drama"),
    BIOGRAPHY("Biography"),
    SPORT("Sport"),
    HISTORY("History"),
    ROMANCE("Romance"),
    WAR("War"),
    MYSTERY("Mystery"),
    ADVENTURE("Adventure"),
    FAMILY("Family"),
    FANTASY("Fantasy"),
    THRILLER("Thriller"),
    HORROR("Horror"),
    FILMNOIR("Film-Noir"),
    ACTION("Action"),
    SCIFI("Sci-fi"),
    COMEDY("Comedy"),
    MUSICAL("Musical"),
    WESTERN("Western"),
    MUSIC("Music"),
    TALKSHOW("Talk-show"),
    DOCUMENTARY("Documentary"),
    ANIMATION("Animation");

    private final String type;

    CategoryType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
