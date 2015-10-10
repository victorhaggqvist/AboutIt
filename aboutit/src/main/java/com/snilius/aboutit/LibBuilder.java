package com.snilius.aboutit;

import android.util.Log;

/**
 * Library builder
 *
 * @author Victor Häggqvist
 * @since 9/17/15
 */
public class LibBuilder {

    private static final String TAG = LibBuilder.class.getSimpleName();

    private String name;
    private String author;
    private LicenseBase license;
    private String url;

    public LibBuilder() {
        name = "";
        author = "";
        license = null;
        url = "";
    }

    public LibBuilder name(String name) {
        this.name = name;
        return this;
    }

    public LibBuilder author(String author) {
        this.author = author;
        return this;
    }

    public LibBuilder license(LicenseBase license) {
        this.license = license;
        return this;
    }

    public LibBuilder url(String url) {
        this.url = url;
        return this;
    }

    public AboutIt.Lib build() {
        if (name == null && author == null && license == null && url == null) {
            Log.d(TAG, "This Library Builder is empty. You should either remove it or fill it out.");
        }
        return new AboutIt.Lib(name, author, license, url);
    }

}
