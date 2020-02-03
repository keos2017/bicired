package com.example.bicired;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.bicired.fragments.GalleryFragment;
import com.example.bicired.fragments.GpsFragment;
import com.example.bicired.fragments.HomeFragment;
import com.example.bicired.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    BottomNavigationView mBottomVavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        showSelectedFragment(new GpsFragment());


        mBottomVavigation =  findViewById(R.id.bottomNavigation);

        mBottomVavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.menu_profile){

                    showSelectedFragment(new ProfileFragment());
                }


                if (menuItem.getItemId() == R.id.menu_home){

                    showSelectedFragment(new HomeFragment());
                }


                if (menuItem.getItemId() == R.id.menu_gps){

                    showSelectedFragment(new GpsFragment());
                }


                if (menuItem.getItemId() == R.id.menu_gallery){

                    showSelectedFragment(new GalleryFragment());
                }

                return true;
            }
        });
    }

    /*
     *   METODO QUE PERMITE ELEJIR EL FRAGMENT
     */

    private void showSelectedFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }




}



