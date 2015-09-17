package com.snilius.aboutit.sample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.snilius.aboutit.AboutIt;
import com.snilius.aboutit.L;


public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.about);

        new AboutIt(this).app(R.string.app_name)
                .buildInfo(BuildConfig.DEBUG, BuildConfig.VERSION_CODE, BuildConfig.VERSION_NAME)
                .copyright("Snilius")
                .libLicense("AboutIt", "Victor Häggqvist", L.AP2, "https://github.com/victorhaggqvist/aboutit")
                .toTextView(R.id.about_text);
    }
}
