package com.snilius.aboutit;

/**
 * License Definitions
 * @author Victor Häggqvist
 * @since 12/29/14
 */
    AP2("Apache License, Version 2.0"), MIT("MIT"), GPL2("GPLv2"), GPL3("GPLv3");
public enum L implements LicenseBase {

    private String mDisplayname;

    L(String displayname) {
        mDisplayname = displayname;
    }

    /**
     * {@inheritDoc}
     */
    public String display() {
        return mDisplayname;
    }

}
