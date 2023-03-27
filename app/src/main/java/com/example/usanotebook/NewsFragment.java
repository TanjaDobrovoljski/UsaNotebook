package com.example.usanotebook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usanotebook.NewsAdapter;
import com.example.usanotebook.NewsArticle;
import com.example.usanotebook.NewsArticleResponse;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsFragment extends Fragment {
    private static final String NEWS_API_KEY = "fd3ce0441b4445bf8d9a2002f3d86e33"; //abb9645ed3feac9e0dc66a2a0a3a2896
    private static final String NEWS_API_BASE_URL = "https://newsapi.org/v2/top-headlines"; //https://gnews.io/api/v4/top-headlines https://newsapi.org/v2/top-headlines

    private RecyclerView newsRecyclerView;
    private NewsAdapter newsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);


        newsRecyclerView = rootView.findViewById(R.id.news_recycler_view);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fetchNewsArticles("sa"); //Saudijska arabija
        return rootView;
    }

    private void fetchNewsArticles(String country) {
        OkHttpClient client = new OkHttpClient();
        String url = NEWS_API_BASE_URL + "?country=" + country + "&apiKey=" + NEWS_API_KEY + "&pageSize=100";


        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Error fetching news articles", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    String responseJson = response.body().string();
                    Type newsListType = new TypeToken<List<NewsArticle>>() {}.getType();

                  //  NewsArticleResponse newsArticleResponse = new Gson().fromJson(responseJson, NewsArticleResponse.class);
                    //List<NewsArticle> newsArticles = newsArticleResponse.getArticles();
                    NewsArticleResponse newsArticleResponse = new Gson().fromJson(responseJson, NewsArticleResponse.class);
                    List<NewsArticle> newsArticles = newsArticleResponse.getArticles();
                    try {


                        // Check if newsArticles is empty
                        if (newsArticles.isEmpty()) {
                            System.out.println("newsArticles list is empty");
                        }
                        getActivity().runOnUiThread(() -> {
                            newsAdapter = new NewsAdapter(newsArticles);
                            newsRecyclerView.setAdapter(newsAdapter);
                        });
                    } catch (JsonSyntaxException e) {
                        System.out.println("Failed to deserialize JSON response: " + e.getMessage());
                        getActivity().runOnUiThread(() -> Toast.makeText(getActivity(), "Error fetching news articles", Toast.LENGTH_SHORT).show());
                    }

                    for (NewsArticle i:newsArticles)
                    {
                        System.out.println("----->"+i.getTitle());
                    }
                    getActivity().runOnUiThread(() -> {

                        newsAdapter = new NewsAdapter(newsArticles);
                        newsRecyclerView.setAdapter(newsAdapter);
                    });
                } else {
                    getActivity().runOnUiThread(() -> Toast.makeText(getActivity(), "Error fetching news articles", Toast.LENGTH_SHORT).show());
                }


            }
        });
    }

}

