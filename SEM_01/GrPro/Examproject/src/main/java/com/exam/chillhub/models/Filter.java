package com.exam.chillhub.models;

import com.exam.chillhub.enums.MediaType;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;

public class Filter extends Model {
    private final StringProperty title;
    private final HashMap<MediaType, Filter> cachedLists;
    private final HashMap<String, Filter> cachedSearches;
    private ObservableList<Media> filteredData;

    public Filter(String title) {
        this.title = new SimpleStringProperty(title);
        filteredData = FXCollections.observableArrayList();
        cachedLists = new HashMap<>();
        cachedSearches = new HashMap<>();
    }

    /**
     * Tilføjer et medie til filteret og tilføjer også mediet til en evt. cached filtrering af dette medies MediaType.
     *
     * @param media Det medie, der skal tilføjes til filteret.
     */
    public void addToFilter(Media media) {
        filteredData.add(media);

        // Tjekker om en cached liste er lavet over denne MediaType og tilføjer i så fald mediet
        if (cachedLists.containsKey(media.getType())) {
            cachedLists.get(media.getType()).addToFilter(media);
        }
    }

    public String getTitle() {
        return this.title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return this.title;
    }

    public ObservableList<Media> getFilteredData() {
        return this.filteredData;
    }

    public void resetFilteredData() {
        filteredData = FXCollections.observableArrayList(filteredData);
    }

    /**
     * Returnerer en liste over det nuværende filter hvor kun serier bliver vist.
     * Metoden gemmer listen så den hurtigere kan blive fundet frem næste gang den samme søgning laves i samme session.
     *
     * @param type MediaType enum som filtreringsgrundlaget
     * @return List<Media> Listen over den filtrerede søgning
     */
    public Filter getFilteredType(MediaType type) {
        // Tjekker om listen allerede er skabt
        if (cachedLists.containsKey(type)) {
            return cachedLists.get(type);
        } else {
            // Alternativt søger den igennem filteret og tilføjer de enkelte elementer til en List der caches i et HashMap
            Filter filterByType = new Filter(getTitle() + " " + type);
            for (Media media : filteredData) {
                if (media.getType() == type) {
                    filterByType.addToFilter(media);
                }
            }
            cachedLists.put(type, filterByType);
            return filterByType;
        }
    }

    /**
     * Basic search returning a filter of all elements that has the searchstring in it's name.
     * Filters can filter only specific media type, i.e. search for only movies with "king" in the name etc.
     *
     * @param searchstring String to be used when searching
     * @return Filter of all media
     */
    public Filter search(String searchstring) {
        if (cachedSearches.containsKey(searchstring))
            return cachedSearches.get(searchstring);

        String[] searchwords = searchstring.split(" ");
        ArrayList<ArrayList<Media>> mediaoccurences = new ArrayList<ArrayList<Media>>();
        for (String s : searchwords) {
            mediaoccurences.add(new ArrayList<>());
        }
        for (Media m : getFilteredData()) {
            String name = m.getName().toLowerCase();
            int occ = 0;
            for (String searchword : searchwords) {
                if (name.contains(searchword.toLowerCase())) {
                    occ++;
                }
            }
            if (occ > 0) {
                mediaoccurences.get(occ - 1).add(m);
            }
        }
        Filter filter = new Filter(searchstring);
        for (int i = searchwords.length - 1; i >= 0; i--) {
            for (Media m : mediaoccurences.get(i)) {
                filter.addToFilter(m);
            }
        }

        cachedSearches.put(searchstring, filter);
        return filter;
    }
}
