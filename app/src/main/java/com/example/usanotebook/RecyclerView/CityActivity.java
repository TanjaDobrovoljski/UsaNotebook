package com.example.usanotebook.RecyclerView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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




        String[] gradovi = {"Madrid", "Pariz", "Barselona"};
        // popunimo listu sa proizvoljnim sadrzajem
        for (String s:gradovi
             ) {
            Item item=new Item(s);
            array.add(item);
        }

        // uzmemo referencu na RecyclerView
        recyclerView = findViewById(R.id.recycler);
        /*recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                // Get the first visible item position
                int firstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                // Iterate through the visible views in the RecyclerView
                for (int i = 0; i < recyclerView.getChildCount(); i++) {
                    View childView = recyclerView.getChildAt(i);
                    int position = recyclerView.getChildAdapterPosition(childView);

                    // Check if the view is a button
                    if (childView instanceof Button) {
                        Button button = (Button) childView;

                        // Example action: Change the background color of buttons when scrolling
                        if (position == firstVisibleItemPosition) {
                            // Button is the first visible item
                            button.setBackgroundColor(Color.RED);
                        } else {
                            // Button is not the first visible item
                            button.setBackgroundColor(Color.BLUE);
                        }
                    }
                }
            }
        });*/
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

        setSupportActionBar(findViewById(R.id.toolbar1));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


}