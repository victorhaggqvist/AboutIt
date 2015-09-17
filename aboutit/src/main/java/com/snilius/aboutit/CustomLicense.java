package com.snilius.aboutit;

/**
 * Custom license holder
 * @author Victor Häggqvist
 * @since 9/17/15
 */
public class CustomLicense implements LicenseBase {

    private String displayName;

    public CustomLicense(String displayName) {

        this.displayName = displayName;
    }

    @Override
    public String display() {
        return displayName;
    }
}
