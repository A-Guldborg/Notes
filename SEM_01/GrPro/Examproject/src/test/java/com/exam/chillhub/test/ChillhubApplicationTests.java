package com.exam.chillhub.test;

import org.junit.jupiter.api.Test;

import static com.exam.chillhub.ChillhubApplication.getResource;
import static com.exam.chillhub.ChillhubApplication.openResource;
import static org.junit.jupiter.api.Assertions.fail;

public class ChillhubApplicationTests {
    static final String resourceName = "movie/posters/ET.jpg";

    @Test
    public void getResource_succeeds() {
        var res = getResource(resourceName);
        if (res == null)
            fail("Resource is null");
    }

    @Test
    public void getResource_fails() {
        var res = getResource("Doesn't exist");
        if (res != null)
            fail("Resource is not null");
    }

    @Test
    public void openResource_succeeds() {
        var res = openResource(resourceName);
        if (res == null)
            fail("Resource is null");
    }

    @Test
    public void openResource_fails() {
        var res = openResource("Doesn't exist");
        if (res != null)
            fail("Resource is not null");
    }
}
