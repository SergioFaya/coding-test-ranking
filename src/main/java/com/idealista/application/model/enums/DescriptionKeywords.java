package com.idealista.application.model.enums;

/**
 * Keywords relevant when computing score using {@link com.idealista.application.model.ad.Ad} description
 */
public enum DescriptionKeywords {

    BRIGHT("Luminoso"), NEW("Nuevo"), DOWNTOWN("Céntrico"), REFORMED("Reformado"), ATTIC("Ático");

    private final String value;

    private DescriptionKeywords(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
