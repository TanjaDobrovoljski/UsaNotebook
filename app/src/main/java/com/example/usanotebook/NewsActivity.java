package com.example.usanotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.usanotebook.NewsFragment;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new NewsFragment())
                .commit();
    }
}