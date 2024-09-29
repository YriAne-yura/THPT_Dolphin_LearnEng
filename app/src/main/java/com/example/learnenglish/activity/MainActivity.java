package com.example.learnenglish.activity;

import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import com.example.learnenglish.fragment.AlphabetFragment;
import com.example.learnenglish.fragment.VocabularyFragment;
import com.example.learnenglish.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.draw_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new VocabularyFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_vocabulary);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_vocabulary:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new VocabularyFragment()).commit();
                break;
            case R.id.nav_learnalphabet:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AlphabetFragment()).commit();
                break;
            case R.id.nav_chat:
                Intent intentChat = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(intentChat);
                break;
            case R.id.nav_setting:
                Intent intentSetting = new Intent(MainActivity.this, MainActivity2_login.class);
                startActivity(intentSetting);
                break;
            case R.id.nav_test:
                Intent intentTest = new Intent(MainActivity.this, SelectUnitActivity.class);
                startActivity(intentTest);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
