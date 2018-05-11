package com.example;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class SystemValue {
    private ResourceBundle bundle;
    private static final String missingProperty = "設定値[{0}]の値はint型でなくてはなりません。設定ファイルを確認してください。";

    public SystemValue() {
        try {
            this.bundle = ResourceBundle.getBundle("SystemValue");
        } catch (MissingResourceException e) {
            throw new GeneratePasswordAppException("指定されたプロパティファイルが存在しません。", e);
        }
    }

    /**
     * 
     * @return
     */
    public int getDefaultLength() throws GeneratePasswordAppException {
        return getNaturalNumber("default.length");
    }

    public int getDefaultLetterCase() throws GeneratePasswordAppException {
        return getNaturalNumber("default.letterCase");
    }

    public boolean getDefaultAcceptSymbolChar() {
        return Boolean.parseBoolean(bundle.getString("default.acceptSymbolChar"));
    }

    public int getMaxPasswordLength() {
        return getNaturalNumber("maxLength");
    }

    public int getOutOfLetterCaseSettings() {
        return getNaturalNumber("outOfLetterCase");
    }

    public int getNaturalNumber(String key) {
        try {
            return Integer.parseInt(bundle.getString(key));
        } catch (NumberFormatException e) {
            throw new GeneratePasswordAppException(MessageFormat.format(missingProperty, key), e);
        }
    }

    public String getErrorDesignCode() {
        return bundle.getString("outputForRed") + "{0}" + bundle.getString("outputForBlack");
    }
}
