package com.ezzy.ishopp;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mNavigationView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        setDrawerLayout();
        handlingselection();

    }

    private void handlingselection() {
        mNavigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private void setDrawerLayout() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new HomeFragment()).commit();

        } else if (id == R.id.nav_cart) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new CartFragment()).commit();
        }
        else if (id == R.id.nav_myOrders) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new MyOrderaFragment()).commit();
        }
        else if (id == R.id.nav_trending) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new TrendingFragment()).commit();
        }
        else if (id == R.id.nav_vendor) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new VendorFragment()).commit();
        }
        else if (id == R.id.nav_offers) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new OffersFragment()).commit();
        }
        else if (id == R.id.nav_category) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new CategorisFragment()).commit();
        }
        else if (id == R.id.nav_profile) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new ProfileFragment()).commit();
        } else if (id == R.id.nav_Help) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new HelpFragment()).commit();
        } else if (id == R.id.nav_contuctUs) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new contuctUsFragment()).commit();
        }



        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}