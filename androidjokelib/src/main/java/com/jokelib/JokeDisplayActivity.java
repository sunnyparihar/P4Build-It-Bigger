package com.jokelib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {


    private TextView jokeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);
        String jokeData= getIntent().getStringExtra("jokeData");

        jokeView=(TextView)findViewById(R.id.joke_text_view);
        if(jokeData!=null)
            jokeView.setText(jokeData);


    }
}
