package com.genesis.contactmanagement.utils;

public class Utilities {

    private Utilities() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isDuplicateVatNumber(String message) {
        return message.contains("Unique index or primary key violation")
                && message.contains("VAT_NUMBER");
    }
}
