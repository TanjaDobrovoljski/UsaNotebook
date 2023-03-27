package com.example.usanotebook.RecyclerView;

public class Item {

    public String title = "";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Item() {
    }
    public Item(String name) {
        this.title=name;
    }
}
