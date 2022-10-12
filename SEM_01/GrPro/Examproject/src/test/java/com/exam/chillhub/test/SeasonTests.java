package com.exam.chillhub.test;

import com.exam.chillhub.models.Season;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SeasonTests {
    Season season;

    @BeforeEach
    public void setUp() {
        season = new Season(1, 2);
    }

    @Test
    public void ctor() {
        assertEquals(season.getSeasonNumber(), 1);
        assertEquals(season.getEpisodes(), 2);
        try {
            new Season(0, -1);
            fail("ctor allowed negative episodes");
        } catch (IllegalArgumentException ignored) {}
    }
}
