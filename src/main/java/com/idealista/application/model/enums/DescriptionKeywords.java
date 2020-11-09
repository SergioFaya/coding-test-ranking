package com.idealista.application.model.enums;

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
