package com.snilius.aboutit.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.snilius.aboutit.AboutIt;
import com.snilius.aboutit.L;
import com.snilius.aboutit.LibBuilder;
import com.snilius.aboutit.meta.LibRetrofit;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView mSampleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        mSampleText = findViewById(R.id.sample_text);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0: // all components
                new AboutIt(this).app("Sample App")
                        .copyright("Example Business")
                        .year(2014)
                        .buildInfo(BuildConfig.DEBUG, BuildConfig.VERSION_CODE, BuildConfig.VERSION_NAME)
                        .description(R.string.sample_description)
                        .libLicense("AboutIt", "Victor Häggqvist", L.AP2, "https://github.com/victorhaggqvist/aboutit")
                        .toTextView(R.id.sample_text);
                break;
            case 1: // dynamic version only
                new AboutIt(this).app("Sample App")
                        .copyright("Example Business")
                        .year(2014)
                        .buildInfo(BuildConfig.DEBUG, BuildConfig.VERSION_CODE, BuildConfig.VERSION_NAME)
                        .toTextView(R.id.sample_text);
                break;
            case 2: // library list
                new AboutIt(this)
                        .libLicense("Lib2", "Random guy", L.MIT, "https://example.com")
                        .libLicense("AboutIt", "Victor Häggqvist", L.AP2, "https://github.com/victorhaggqvist/aboutit")
                        .toTextView(R.id.sample_text);
                break;
            case 3: // custom version
                new AboutIt(this).app("Sample App")
                        .copyright("Example Business")
                        .buildInfo(BuildConfig.DEBUG, BuildConfig.VERSION_CODE, BuildConfig.VERSION_NAME)
                        .release("beta")
                        .toTextView(R.id.sample_text);
                break;
            case 4: // version string
                String versionString = new AboutIt(this)
                        .buildInfo(BuildConfig.DEBUG, BuildConfig.VERSION_CODE, BuildConfig.VERSION_NAME)
                        .getVersionString();
                mSampleText.setText(versionString);
                break;
            case 5:
                new AboutIt(this)
                        .libLicense(new LibBuilder().name("").author("").license(L.GPL2).url("").build())
                        .libLicense(new LibBuilder().name("Awesome Lib").author("Nucleus").license(L.BSD).build())
                        .libLicense(new LibRetrofit());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
