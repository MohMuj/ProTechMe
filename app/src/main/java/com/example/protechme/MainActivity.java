package com.example.protechme;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.Format;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        connectionclass cn = new connectionclass();



        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.Measures:
                        selectedFragment = new Measures();
                        hun();
                        break;
                    case R.id.history:
                        selectedFragment = History.newInstance();
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case R.id.notification:
                        selectedFragment = new Notification();
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case R.id.blog:
                        selectedFragment = Blog.newInstance("mo", "mo");
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case R.id.offers:
                        selectedFragment = OffersFragment.newInstance("mo", "mo");
                        handler.removeCallbacksAndMessages(null);
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, selectedFragment);
                transaction.commit();
                return true;
            }

        });
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, Measures.newInstance());
        transaction.commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.aboutus) {
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.logout) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void hun() {
        final int INTERVAL = 1000 * 10; //30 secound
        handler.postDelayed(new Runnable() {
            final int min = 78;
            final int max = 83;

            @Override
            public void run() {
                TextView heartbeat = (TextView) findViewById(R.id.heartbeat);
                TextView spo2 = (TextView) findViewById(R.id.spo2);
                TextView temp = (TextView) findViewById(R.id.temp);


                final int random = new Random().nextInt((max - min) + 1) + min;
                final int random2 = new Random().nextInt((100 - 90) + 1) + 90;
                final int random3 = new Random().nextInt((37 - 36) + 1) + 36;


                heartbeat.setText(Integer.toString(random));
                spo2.setText(Integer.toString(random2) + "%");
                temp.setText(Integer.toString(random3));


                //Do something after 20 seconds
                handler.postDelayed(this, INTERVAL);
            }
        }, 200);
    }
}
