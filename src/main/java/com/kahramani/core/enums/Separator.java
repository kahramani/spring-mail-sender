package com.kahramani.core.enums;

/**
 * Created by kahramani on 11/17/2016.
 */
public enum Separator {
    DOT('.'),
    COMMA(','),
    COLON(':'),
    SEMICOLON(';'),
    SLASH('/'),
    HYPHEN('-'),
    AT_SIGN('@');

    private char punctuation;

    Separator(char punctuation) {
        this.punctuation = punctuation;
    }

    public char getPunctuation() {
        return punctuation;
    }
}
