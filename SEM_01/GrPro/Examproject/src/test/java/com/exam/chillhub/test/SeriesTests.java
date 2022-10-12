package com.exam.chillhub.test;

import com.exam.chillhub.enums.MediaType;
import com.exam.chillhub.models.Series;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeriesTests {
    Series series;

    @BeforeEach
    public void setUp() {
        series = new Series("24", "2012", 1, false, 0);
    }

    @Test
    public void ctor() {
        assertEquals(series.getType(), MediaType.SERIES);
        assertEquals(series.getName(), "24");
        assertEquals(series.getYear(), "2012");
        assertEquals(series.getRating(), 1);
        assertEquals(series.getFavorite(), false);
        assertEquals(series.getIdx(), 0);
        assertEquals(series.getSeasons().size(), 0);
    }

    @Test
    public void addSeason() {
        assertEquals(series.getNumberOfSeasons(), 0);
        series.addSeason(0, 0);
        series.addSeason(0, 0);
        assertEquals(series.getNumberOfSeasons(), 2);
    }

    @Test
    public void getNumberOfEpisodes() {
        assertEquals(series.getNumberOfEpisodes(), 0);
        series.addSeason(0, 3);
        series.addSeason(0, 4);
        assertEquals(series.getNumberOfEpisodes(), 7);
        series.addSeason(0, 1);
        assertEquals(series.getNumberOfEpisodes(), 8);
    }
}
