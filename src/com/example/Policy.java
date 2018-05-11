package com.example;

public class Policy {

    public Policy() {
        // TODO Auto-generated constructor stub
    }

    private int passwordLength;
    private int letterCase;
    private boolean acceptSymbolChar;

    public int getPasswordLength() {
        return passwordLength;
    }

    public void setPasswordLength(int passwordLength) {
        this.passwordLength = passwordLength;
    }

    public int getLetterCase() {
        return letterCase;
    }

    public void setLetterCase(int letterCase) {
        this.letterCase = letterCase;
    }

    public boolean isAcceptSymbolChar() {
        return acceptSymbolChar;
    }

    public void setAcceptSymbolChar(boolean acceptSymbolChar) {
        this.acceptSymbolChar = acceptSymbolChar;
    }

}
