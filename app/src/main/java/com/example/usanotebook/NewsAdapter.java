package com.example.usanotebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<com.example.usanotebook.NewsArticle> newsArticles;

    public NewsAdapter(List<NewsArticle> newsArticles) {
        this.newsArticles = newsArticles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        com.example.usanotebook.NewsArticle newsArticle = newsArticles.get(position);
        holder.titleTextView.setText(newsArticle.getTitle());
        holder.descriptionTextView.setText(newsArticle.getDescription());
        holder.publishedAtTextView.setText(newsArticle.getPublishedAt());System.out.println("poslije ulaska");
        Picasso.get().load(newsArticle.getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return newsArticles.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;
        TextView publishedAtTextView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.news_image_view);
            titleTextView = itemView.findViewById(R.id.news_title_text_view);
            descriptionTextView = itemView.findViewById(R.id.news_description_text_view);
            publishedAtTextView = itemView.findViewById(R.id.news_date_text_view);
        }
    }
}

