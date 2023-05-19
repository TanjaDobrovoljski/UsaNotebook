package com.example.usanotebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.usanotebook.NewsFragment;

public class NewsActivity extends AppCompatActivity implements NewsAdapter.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        setSupportActionBar(findViewById(R.id.toolbar));
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new NewsFragment())
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(NewsArticle article) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("article", article);

        SpecificNewsFragment detailFragment = new SpecificNewsFragment();
        detailFragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null)
                .commit();
    }



}
