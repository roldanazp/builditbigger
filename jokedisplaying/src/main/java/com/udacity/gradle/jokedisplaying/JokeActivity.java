package com.udacity.gradle.jokedisplaying;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {
    public static final String JOKE_EXTRA = "joke_extra";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        if(getIntent().hasExtra(JOKE_EXTRA)){
            String joke = getIntent().getStringExtra(JOKE_EXTRA);
            TextView jokeView = findViewById(R.id.joke);
            jokeView.setText(joke);
        }
    }
}
