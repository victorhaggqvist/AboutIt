package com.snilius.aboutit;

import android.app.Activity;
import android.text.util.Linkify;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <h1>AboutIt</h1>
 * A About-page creator
 *
 * <pre>
 * {@code
 * new AboutIt(this).app(R.string.app_name)
 *     .buildInfo(BuildConfig.DEBUG, BuildConfig.VERSION_CODE, BuildConfig.VERSION_NAME)
 *     .copyright("Example Business")
 *     .libLicense("AboutIt", "Victor Häggqvist", L.AP2, "https://github.com/victorhaggqvist/aboutit")
 *     .toTextView(R.id.about_text);
 * }
 * </pre>
 *
 * @author Victor Häggqvist
 * @since 12/29/14
 * @version 1.0
 */
public class AboutIt {

    private String copyright = null;
    private int year = 0;
    private int endYear = 0;
    private List<Lib> libs = new ArrayList<>();
    private Activity activity;
    private String appName = null;
    private boolean debug;
    private int versionCode;
    private String versionName;
    private String releaseName = null;
    private String description = null;

    /**
     * Create a page generator
     * @param activity The aboutpage activity
     */
    public AboutIt(Activity activity) {

        this.activity = activity;
    }

    /**
     * Generate text and put in @ref{about_text}
     * @param about_text Resource it of destination TextView
     */
    @SuppressWarnings("StringConcatenationInsideStringBufferAppend")
    public void toTextView(int about_text) {
        TextView out = (TextView) activity.findViewById(about_text);
        out.setAutoLinkMask(Linkify.WEB_URLS);

        endYear = endYear();

        StringBuilder sb = new StringBuilder();
        if (appName != null)
            sb.append(appName+" v"+getVersionString()+"\n");

        if (copyright != null) {
            sb.append("Copyright (c) ");
            if (year != 0)
                sb.append(year + (endYear != 0 ? " - " + endYear : "") + " ");
            sb.append(copyright + "\n\n");
        }

        if (description != null)
            sb.append(description+"\n\n");

        // Sort library list alphabeticly by name
        Collections.sort(libs, new Comparator<Lib>() {
            @Override
            public int compare(Lib lhs, Lib rhs) {
                return lhs.name.compareTo(rhs.name);
            }
        });

        for(Lib l:libs){
            sb.append(l.name + " by " + l.author + " under " + l.license.display() + ", " + l.url + "\n");
        }

        out.setText(sb.toString());
    }

    /**
     * Get a baked version string.
     * Version string is build from build info and release name
     * It will look something like '1.42 (42-debug)'
     * @return baked version string
     */
    public String getVersionString() {
        releaseName = "-"+(releaseName != null?releaseName:(debug?"debug":""));
        return versionName+" ("+versionCode+releaseName+")";
    }

    /**
     * Demiter what endyear to show
     * @return year to show
     */
    private int endYear() {
        if (endYear != 0) {
            return endYear;
        } else {
            Calendar now = Calendar.getInstance();
            int yearNow = now.get(Calendar.YEAR);
            if (year == yearNow)
                return 0;
            else
                return yearNow;
        }
    }

    /**
     * Get String by id
     * @param stringid String id
     * @return String
     */
    private String s(int stringid) {
        return activity.getString(stringid);
    }

    /**
     * Copyright name
     * @param copyright Name
     */
    public AboutIt copyright(String copyright){
        this.copyright = copyright;
        return this;
    }

    /**
     * Start copyright year.
     * If there is no start year no year will be displayed at all.
     * @param year Year
     */
    public AboutIt year(int year) {
        this.year = year;
        return this;
    }

    /**
     * Add a library to the list
     * @param name      Name of Library
     * @param author    Author of library
     * @param license   Library licanse, defined by L
     * @param url       Url to or otherwise referense to library
     * @see L
     */
    public AboutIt libLicense(String name, String author, L license, String url) {
        libs.add(new Lib(name, author, license, url));
        return this;
    }

    /**
     * Appname to display
     * @param stringresource A string resource id
     */
    public AboutIt app(int stringresource) {
        this.appName = s(stringresource);
        return this;
    }

    /**
     * Appname to display
     * @see #app(int)
     */
    public AboutIt app(String appName) {
        this.appName = appName;
        return this;
    }

    /**
     * Set a custom release name. Override the default name eg. beta
     * @param releaseName The release name
     */
    public AboutIt release(String releaseName){
        this.releaseName = releaseName;
        return this;
    }

    /**
     * App build info. To be used in conjunktion with the BuildConfig class
     * {@code .buildInfo(BuildConfig.DEBUG, BuildConfig.VERSION_CODE, BuildConfig.VERSION_NAME)}
     *
     * @param debug         If is debug build
     * @param versionCode   Version code
     * @param versionName   Version name
     */
    public AboutIt buildInfo(boolean debug, int versionCode, String versionName) {
        this.debug = debug;
        this.versionCode = versionCode;
        this.versionName = versionName;
        return this;
    }

    /**
     * A longer description
     * @param stringresource A string resource id
     */
    public AboutIt description(int stringresource) {
        description = s(stringresource);
        return this;
    }

    /**
     * A longer description
     * @param description The description
     * @see #description(int)
     */
    public AboutIt description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Holder for libraries
     */
    private class Lib {

        final String name;
        final String author;
        final L license;
        final String url;

        public Lib(String name, String author, L license, String url) {

            this.name = name;
            this.author = author;
            this.license = license;
            this.url = url;
        }
    }
}
