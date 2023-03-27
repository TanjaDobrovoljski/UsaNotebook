package com.example.usanotebook;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsApi {
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static final String API_KEY = "fd3ce0441b4445bf8d9a2002f3d86e33";

    private OkHttpClient client = new OkHttpClient();

    public String getNews(String country) throws IOException {
        String url = BASE_URL + "top-headlines?country=" + country + "&apiKey=" + API_KEY;
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
