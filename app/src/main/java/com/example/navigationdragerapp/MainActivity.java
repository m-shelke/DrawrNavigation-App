package com.example.navigationdragerapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawerLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadFragmet(new HomeFragment(),true);

         drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navigationView);
        Toolbar toolbar = findViewById(R.id.tooBar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("NavigationDrawer");
        getSupportActionBar().setSubtitle("Slide from left for Navigation");
        toolbar.setSubtitleTextColor(getColor(R.color.black));
        toolbar.setTitleTextColor(getColor(R.color.black));
        toolbar.setBackgroundColor(getColor(R.color.white));

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer,R.string.closeDrawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

                if (itemId == R.id.menuHome){
                    loadFragmet(new HomeFragment(),false);
                } else if (itemId == R.id.menuCategory) {
                    Toast.makeText(MainActivity.this, "Category Clicked", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.menuCards) {
                    loadFragmet(new CardsFragment(),false);
                } else if (itemId == R.id.menuAccount) {
                    Toast.makeText(MainActivity.this, "Account clicked", Toast.LENGTH_SHORT).show();
                }

                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });



    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }


    public void loadFragmet(Fragment fragment, boolean check){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (check){
            fragmentTransaction.add(R.id.frameLayout,fragment);
        }else {
            fragmentTransaction.replace(R.id.frameLayout,fragment);
        }
        fragmentTransaction.commit();
    }


}