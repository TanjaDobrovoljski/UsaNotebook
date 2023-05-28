package Sights;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;


import com.example.usanotebook.R;



public class SpecificSightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_sight);
        setSupportActionBar(findViewById(R.id.toolbar));


        String title = getIntent().getStringExtra("title");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       getSupportActionBar().setTitle(title);
        Bundle args = new Bundle();
        args.putString("title", title);
        SightFragment fragment=new SightFragment();




        fragment.setArguments(args);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment instanceof MapsFragment) {
            ((MapsFragment) fragment).handleBackPressed();
        }
        else if(fragment instanceof SightFragment)
        {
            ((SightFragment)fragment).handleBackPressed();
            super.onBackPressed();
        }
    }


}