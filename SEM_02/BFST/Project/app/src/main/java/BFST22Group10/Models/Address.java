package BFST22Group10.Models;

import java.io.Serializable;
import java.util.regex.Pattern;

public class Address implements Serializable, Pointable {
    public static final long serialVersionUID = 16;
    private float lat;
    private float lon;
    private String city;
    private String housenumber;
    private String postcode;
    private String street;

    private final static String REGEX = "^(?<street>[A-Za-zæøåÆØÅéÉ\\- ]+?) *(?<house>[0-9]+[A-Za-zæøåÆØÅ]?)?[., ]*(?<floor>[0-9]+)?[., ]*(?<side>[0-9tvhTHV]+)?([, ]+(?<postcode>[0-9]{4})? +(?<city>[A-Za-zæøÆØÅå ]+))?$";
    private final static Pattern PATTERN = Pattern.compile(REGEX);

    public static Address valueOf(String input) {
        Address address = new Address();

        var matcher = PATTERN.matcher(input);
        if (matcher.matches()) {
            address.setStreet(matcher.group("street"));
            address.setHousenumber(matcher.group("house"));
            address.setPostcode(matcher.group("postcode"));
            address.setCity(matcher.group("city"));
            return address;
        } else {
            return null;
        }
    }

    @Override
    public String toString(){
        StringBuilder addressString = new StringBuilder();
        for (String s : new String[]{street, housenumber, postcode, city}) {
            if (s != null) {
                addressString.append(s).append(' ');
            }
        }
        return addressString.toString().trim();
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }
    
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getStreet() {
        return street;
    }

    @Override
    public float getMainLat() {
        return lat;
    }

    @Override
    public float getMainLon() {
        return lon;
    }
}
