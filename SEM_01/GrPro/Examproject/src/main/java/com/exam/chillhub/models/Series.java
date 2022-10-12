package com.exam.chillhub.models;

import com.exam.chillhub.enums.MediaType;

import java.util.ArrayList;
import java.util.List;

public class Series extends Media {
    private List<Season> seasons;

    public Series(String name, String year, double rating, boolean favorite, int idx) {
        super(MediaType.SERIES, name, year, rating, favorite, idx);
        this.seasons = new ArrayList<>();
    }

    public int getNumberOfSeasons() {
        return seasons.size();
    }

    public int getNumberOfEpisodes() {
        int totalEpisodes = 0;
        for (Season season : seasons) {
            totalEpisodes += season.getEpisodes();
        }
        return totalEpisodes;
    }

    public void addSeason(int seasonNumber, int episodes) {
        seasons.add(new Season(seasonNumber, episodes));
    }

    public List<Season> getSeasons() {
        return this.seasons;
    }
}
