package com.example.usanotebook.RecyclerView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import  com.example.usanotebook.RecyclerView.MyRecyclerAdapter;

import com.example.usanotebook.R;
import com.example.usanotebook.WelcomeActivity;

import java.util.ArrayList;
import java.util.List;

public class CityActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Item> array = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String[] gradovi = {"Madrid", "Pariz", "Barselona"};
        // popunimo listu sa proizvoljnim sadrzajem
        for (String s:gradovi
             ) {
            Item item=new Item(s);
            array.add(item);
        }

        // uzmemo referencu na RecyclerView
        recyclerView = findViewById(R.id.recycler);
        // optimizacija
        recyclerView.setHasFixedSize(true);
        // postavimo vrstu LayoutManager-a
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // proslijedimo Adapter-u podatke iz liste
        // mAdapter = new Adapter(array);
        mAdapter = new MyRecyclerAdapter(array, new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Item item) {
                Toast.makeText(getApplicationContext(), "Kliknuli ste na: " + item.getTitle(), Toast.LENGTH_LONG).show();
            }
        });
        // povezemo RecyclerView sa adapterom
        recyclerView.setAdapter(mAdapter);
    }

}