package com.example.usanotebook;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class NewsArticle implements Parcelable {
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("url")
    private String url;
    @SerializedName("urlToImage")
    private String imageUrl;
    @SerializedName("publishedAt")
    private String publishedAt;

    // constructor, getters, and setters

    public NewsArticle(String title, String description, String url, String imageUrl, String publishedAt) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.imageUrl = imageUrl;
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    // Constructor that takes a Parcel and initializes the NewsArticle object
    public NewsArticle(Parcel parcel) {
        title = parcel.readString();
        description = parcel.readString();
        url = parcel.readString();
        imageUrl = parcel.readString();
        publishedAt = parcel.readString();
    }

    // Creator for the Parcelable interface
    public static final Creator<NewsArticle> CREATOR = new Creator<NewsArticle>() {
        @Override
        public NewsArticle createFromParcel(Parcel parcel) {
            return new NewsArticle(parcel);
        }

        @Override
        public NewsArticle[] newArray(int i) {
            return new NewsArticle[i];
        }
    };

    // Write object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(url);
        parcel.writeString(imageUrl);
        parcel.writeString(publishedAt);
    }

    // Return flags for parcelable object
    @Override
    public int describeContents() {
        return 0;
    }

    // getters and setters
}


