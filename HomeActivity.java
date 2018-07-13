package com.codepath.chattyboi.parsetagram;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class HomeActivity extends AppCompatActivity {

    //create bottom navigation view and assign it a value
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final FragmentManager fragmentManager = getSupportFragmentManager();

        final Fragment fragmentTimeline = new timelineFragment();
        final Fragment fragmentCompose = new composeFragment();

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flContainer, fragmentTimeline).commit();

        //create bottom navigation view

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_timeline:
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.flContainer, fragmentTimeline).commit();
                        return true;
                    case R.id.action_compose:
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.flContainer, fragmentCompose).commit();
                        return true;
                    case R.id.action_profile:
                        /*fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.flContainer, fragmentTimeline).commit();*/
                        return true;
                }
                //if id is not the one
                return false;
            }
        });
    }

    public void changeToHome(){
        bottomNavigationView.setSelectedItemId(R.id.action_timeline);
    }

}
