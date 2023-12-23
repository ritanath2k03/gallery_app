package com.ritanath.imagegallery;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.ritanath.imagegallery.Model.Photo;
import com.ritanath.imagegallery.Model.Photos;
import com.ritanath.imagegallery.Viewmodel.FlickrViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.HiltAndroidApp;


@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FlickrViewModel flickrViewModel;
    private FlickrAdapter flickrAdapter;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        setNavView();
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Grid layout with 2 columns

        flickrAdapter = new FlickrAdapter(this);
        recyclerView.setAdapter(flickrAdapter);

        // Initialize ViewModel using Hilt or manually inject dependencies
        flickrViewModel = new ViewModelProvider(this).get(FlickrViewModel.class);


        // Fetch recent photos
        String apiKey = "6f102c62f41998d151e5a1b48713cf13";
        observeRecentPhotos(apiKey);
    }

    private void setNavView() {
   Toolbar toolbar;

        toolbar=findViewById(R.id.toolbar);
        drawerLayout=findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


    }

    private void observeRecentPhotos(String apiKey) {
        Toast.makeText(this, "MAIN WORKING", Toast.LENGTH_SHORT).show();

        flickrViewModel.getRecentPhotos(apiKey).observe(this, photos -> {

            if (photos != null) {
                Log.d("Status","working");
                flickrAdapter.setPhotos(photos);

                flickrAdapter.notifyDataSetChanged();

                Toast.makeText(this, "Inside if", Toast.LENGTH_SHORT).show();
            }

        });


        Toast.makeText(this, "Outside if", Toast.LENGTH_SHORT).show();
    }
}