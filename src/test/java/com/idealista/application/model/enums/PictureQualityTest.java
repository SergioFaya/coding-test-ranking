package com.idealista.application.model.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PictureQualityTest {

    @Test
    public void assertPictureQuality(){
        var expectedSize = 2;
        assertEquals(expectedSize , PictureQuality.values().length);
        assertEquals("HD", PictureQuality.HD .name());
        assertEquals("SD", PictureQuality.SD .name());
    }
}
