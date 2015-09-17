package com.snilius.aboutit;

/**
 * Library builder
 * @author Victor Häggqvist
 * @since 9/17/15
 */
public class LibBuilder {

    private String name;
    private String author;
    private LicenseBase license;
    private String url;

    public LibBuilder() {
        name = "";
        author = "";
        license= null;
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
        return new AboutIt.Lib(name, author, license,url);
    }

}
