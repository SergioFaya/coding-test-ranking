package com.idealista.application.model.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AdTypologyTest {

    @Test
    public void assertAdTypology(){
        var expectedSize = 3;
        assertEquals(expectedSize , AdTypology.values().length);
        assertEquals("FLAT", AdTypology.FLAT .name());
        assertEquals("CHALET", AdTypology.CHALET .name());
        assertEquals("GARAGE", AdTypology.GARAGE .name());
    }
}
