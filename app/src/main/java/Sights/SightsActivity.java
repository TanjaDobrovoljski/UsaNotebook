package Sights;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usanotebook.NewsActivity;
import com.example.usanotebook.R;
import com.example.usanotebook.RecyclerView.CityActivity;
import com.example.usanotebook.WelcomeActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SightsActivity extends AppCompatActivity implements View.OnClickListener {

    private SightApi sightApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sights);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.dugme).setOnClickListener(this);
        findViewById(R.id.dugme2).setOnClickListener(this);
        /*sightApi = new SightApi();
        final ConstraintLayout layout = findViewById(R.id.root);
        final Map<Button, TextView> buttonTextViewMap = new HashMap<>();

        Button button=findViewById(R.id.dugme);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the SightFragment
                SightFragment sightFragment = new SightFragment();

                // Pass the sight name as an argument to the fragment
                Bundle bundle = new Bundle();
                bundle.putString("Statue of Liberty", button.getText().toString());
                sightFragment.setArguments(bundle);

                // Replace the current fragment with the SightFragment
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, sightFragment)
                        .addToBackStack(null)
                        .commit();

            }
        });*/

    }
   /* @Override
    public void onClick(View view) {
        Fragment fragment = null;

        switch (view.getId()) {
            case R.id.dugme:
                fragment = new SightFragment();
                break;

           /* case R.id.buttonSights:
                intent = new Intent(WelcomeActivity.this, SightsActivity.class);
                break;
            case R.id.buttonNews:
                intent = new Intent(WelcomeActivity.this, NewsActivity.class);
                break;
            case R.id.buttonGeneral:
                // intent = new Intent(WelcomeActivity.this, SightsActivity.class);
                break;*/

       /* if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, fragment); // Replace "fragmentContainer" with the ID of the container where you want to display the fragment
            transaction.addToBackStack(null); // Add the fragment to the back stack
            transaction.commit();
        }
    }*/

    @Override
    public void onClick(View view) {
        Intent intent = null;
        String title="";


        switch (view.getId()) {
            case R.id.dugme:
                intent = new Intent(SightsActivity.this, SpecificSightActivity.class);
                title=((Button) findViewById(R.id.dugme)).getText().toString();
                intent.putExtra("title",title);
                break;
           // case R.id.dugme2:
             //   intent = new Intent(WelcomeActivity.this, SightsActivity.class);
               // break;

        }
        startActivity(intent);
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