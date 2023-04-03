package com.example.usanotebook;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SpecificNewsFragment extends Fragment {

        public SpecificNewsFragment() {
            // Required empty public constructor
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_specific_news, container, false);

            // Get the NewsArticle object passed as an argument
            Bundle bundle = getArguments();
            NewsArticle newsArticle = bundle.getParcelable("newsArticle");

            // Update the UI with the NewsArticle data
            TextView titleTextView = rootView.findViewById(R.id.news_title);
            titleTextView.setText(newsArticle.getTitle());


            TextView descriptionTextView = rootView.findViewById(R.id.news_content);
            descriptionTextView.setText(newsArticle.getDescription());

            ImageView imageView = rootView.findViewById(R.id.news_image);
            Picasso.get().load(newsArticle.getImageUrl()).into(imageView);


            return rootView;
        }
    }
