package com.snilius.aboutit;

/**
 * License Definitions
 * @author Victor Häggqvist
 * @since 12/29/14
 */
public enum L implements LicenseBase {
    AP2("Apache License, Version 2.0"), MIT("MIT"),
    GPL2("GPL v2.0"), GPL3("GPL v3.0"), AGPL3("Affero GPL v3.0"),
    BSD("BSD"), SBSD("Simplified BSD"), NBSD("New BSD");

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
