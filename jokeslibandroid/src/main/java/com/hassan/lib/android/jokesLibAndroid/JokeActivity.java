package com.hassan.lib.android.jokesLibAndroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String INTENT_EXTRA_KEY_JOKE = "joke_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        // Get the intent passed to this activity to retrieve the data
        // and set the joke to the text view inside this activity
        Intent intent = getIntent();
        if(intent.hasExtra(INTENT_EXTRA_KEY_JOKE)) {
            TextView tvJoke = (TextView) findViewById(R.id.tvJoke);
            tvJoke.setText(intent.getStringExtra(INTENT_EXTRA_KEY_JOKE));
        }
    }
}
