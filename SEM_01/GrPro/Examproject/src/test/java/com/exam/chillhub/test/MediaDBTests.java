package com.exam.chillhub.test;

import com.exam.chillhub.database.MediaDB;
import com.exam.chillhub.enums.CategoryType;
import com.exam.chillhub.models.Filter;
import com.exam.chillhub.models.Media;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MediaDBTests {
    private Filter media;
    private Map<CategoryType, Filter> categories;

    @BeforeEach
    public void setUp() {
        media = MediaDB.getInstance().getDB();
        categories = MediaDB.getInstance().getCategories();
    }

    @Test
    public void testAllCategoriesExists() {
        CategoryType[] expected = {CategoryType.CRIME, CategoryType.DRAMA, CategoryType.BIOGRAPHY, CategoryType.SPORT, CategoryType.HISTORY, CategoryType.ROMANCE, CategoryType.WAR, CategoryType.MYSTERY, CategoryType.ADVENTURE, CategoryType.FAMILY, CategoryType.FANTASY, CategoryType.THRILLER, CategoryType.HORROR, CategoryType.FILMNOIR, CategoryType.ACTION, CategoryType.SCIFI, CategoryType.COMEDY, CategoryType.MUSICAL, CategoryType.WESTERN, CategoryType.MUSIC, CategoryType.TALKSHOW, CategoryType.DOCUMENTARY, CategoryType.ANIMATION};
        for (CategoryType category : expected) {
            assertTrue(categories.containsKey(category), "missing: " + category);
            assertTrue(categories.get(category).getFilteredData().size() > 0, category + " is empty");
        }
    }

    @Test
    public void testAllMediaExists() {
        assertEquals(200, media.getFilteredData().size(), "Should read 200 media but reads: " + media.getFilteredData().size() + " instead");
    }

    @Test
    public void testSearchQuery_star() {
        String searchQuery = "StAr";
        Filter searchfilter = MediaDB.getInstance().getDB().search(searchQuery);
        List<Media> filteredData = searchfilter.getFilteredData();
        assertEquals(filteredData.size(), 3, "Should find 3 movies but found " + filteredData.size());
        for (Media m : filteredData) {
            assertTrue(m.getName().toLowerCase().contains(searchQuery.toLowerCase()), "A media was found without star in the name: " + m.getName());
        }
    }

    @Test
    public void testSeveralWordsSearchQuery() {
        String searchQuery = "rain Man";
        Filter searchFilter = MediaDB.getInstance().getDB().search(searchQuery);
        List<Media> filteredData = searchFilter.getFilteredData();
        assertEquals(filteredData.get(0).getName(), "Rain Man", "Should find Rain Man as the first move but found " + filteredData.get(0).getName() + " instead");
        assertEquals(filteredData.size(), 6, "Expected 6 media but found " + filteredData.size() +  " media instead when searching for Rain Man");
        for (Media m : filteredData) {
            if (!(m.getName().toLowerCase().contains("rain") || m.getName().toLowerCase().contains("man"))) {
                fail(m.getName() + " was found in the search query but does not contain rain or man");
            }
        }
    }
}
