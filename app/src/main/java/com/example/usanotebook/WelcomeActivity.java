package com.example.usanotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;


import com.example.usanotebook.RecyclerView.CityActivity;

import Settings.SettingsActivity;
import Sights.SightsActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setTitle(getString(R.string.app_name));



        ImageView image=(ImageView)findViewById(R.id.imageView);
        int imageResource=getResources().getIdentifier("drawable/background",null,this.getPackageName());
        image.setImageResource(imageResource);
        ImageView image2=(ImageView)findViewById(R.id.imageView2);
        int imageResource2=getResources().getIdentifier("@drawable/background2",null,this.getPackageName());
        image2.setImageResource(imageResource2);

        findViewById(R.id.buttonCities).setOnClickListener(this);
        findViewById(R.id.buttonSights).setOnClickListener(this);
        findViewById(R.id.buttonNews).setOnClickListener(this);
        findViewById(R.id.buttonGeneral).setOnClickListener(this);

    }



        /*
        createGroupList();
        createCollection();

        expandableListView=findViewById(R.id.citiesExpandableListView);
        expandableListAdapter=new MyExpandableListAdapter(this,groupList,welcomeActivityItems);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                String selected = expandableListAdapter.getChild(i,i1).toString();
                Toast.makeText(getApplicationContext(), "Selected: " + selected, Toast.LENGTH_SHORT).show();
                return true;
            }
        });


       /* citiesExpandableListView = findViewById(R.id.citiesExpandableListView);

        // Initialize the data for the expandable list view
        citiesData = new ArrayList<>();

        citiesData.add("Paris");
        citiesData.add("Rome");
        citiesData.add("Barcelona");


        ExpandableListView expandableListView = new ExpandableListView(this);
        View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.activity_welcome, null);
        ConstraintLayout constraintLayout = view.findViewById(R.id.myConstraintLayout);
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.topToBottom = R.id.imageView; // Set the top constraint to your ImageView
        layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID; // Set the bottom constraint to the parent
        expandableListView.setLayoutParams(layoutParams);
        ExpandableListAdapter adapter = new ExpandableListAdapter(this, citiesData);
        expandableListView.setAdapter(adapter);
        constraintLayout.addView(expandableListView); // Add the ExpandableListView to your layout*/


/*
    private void createCollection() {
        String[] gradovi = {"Madrid", "Pariz", "Barselona"};
        String[] znamenitost = {"trg", "kapija", "fontana"};
        String[] uopsteno = {"grb", "zastava", "himnna"};

        welcomeActivityItems = new HashMap<String,List<String>>();

        // Add the child items directly to the map with the group name as the key
        welcomeActivityItems.put("Gradovi", Arrays.asList(gradovi));
        welcomeActivityItems.put("znamenitosti", Arrays.asList(znamenitost));
        welcomeActivityItems.put("Uopstenmo", Arrays.asList(uopsteno));
    }

    private void loadChild(String[] stvari) {
        childList=new ArrayList<>();
        for (String child:stvari
             ) {
            childList.add(child);

        }
    }

    private void createGroupList() {
        groupList=new ArrayList<>();
        groupList.add("Gradovi");
        groupList.add("znamenitosti");
        groupList.add("Istorija");
        groupList.add("Uopstenmo");

    }
*/

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main,menu);

        return  true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.app_settings) {
            // Handle the click on the "Open Activity" menu item
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        // Handle other menu item clicks if needed

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;

        switch (view.getId()) {
            case R.id.buttonCities:
                intent = new Intent(WelcomeActivity.this, CityActivity.class);
                break;
            case R.id.buttonSights:
                intent = new Intent(WelcomeActivity.this, SightsActivity.class);
                break;
            case R.id.buttonNews:
                intent = new Intent(WelcomeActivity.this, NewsActivity.class);
                break;
            case R.id.buttonGeneral:
               // intent = new Intent(WelcomeActivity.this, SightsActivity.class);
                break;
        }
        startActivity(intent);
    }


    /*
    public void showOptions(View view)
    {
        list.setVisibility(View.VISIBLE);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.options_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        list.setAdapter(adapter);
    }*/
}