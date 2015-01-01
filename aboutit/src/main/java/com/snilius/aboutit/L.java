package com.snilius.aboutit;

/**
 * License Definitions
 * @author Victor Häggqvist
 * @since 12/29/14
 */
public enum L {
    AP2("Apache License, Version 2.0"), MIT("MIT"), GPL2("GPLv2"), GPL3("GPLv3");

    private String mDisplayname;

    L(String displayname) {
        mDisplayname = displayname;
    }

    /**
     * Get diaplayname for license
     * @return displaname
     */
    public String display() {
        return mDisplayname;
    }
}
