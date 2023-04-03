package com.example.usanotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.usanotebook.NewsFragment;

public class NewsActivity extends AppCompatActivity implements NewsAdapter.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new NewsFragment())
                .commit();
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
