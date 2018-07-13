package com.codepath.chattyboi.parsetagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.parse.ParseUser;

public class HomeActivity extends AppCompatActivity {

    //create bottom navigation view and assign it a value
    private BottomNavigationView bottomNavigationView;

    private Toolbar toolbar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_top_logout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.logout_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().show();

        final FragmentManager fragmentManager = getSupportFragmentManager();

        final Fragment fragmentTimeline = new timelineFragment();
        final Fragment fragmentCompose = new composeFragment();

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flContainer, fragmentTimeline).commit();

        //setup top navigation view
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null

                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);

                return false;
            }
        });

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

            /*logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null

                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });*/

    //called after posting: returning to timeline fragment
    public void returnToTimeline(){
        bottomNavigationView.setSelectedItemId(R.id.action_timeline);
    }

}
