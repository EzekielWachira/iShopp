package com.ezzy.ishopp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.ezzy.ishopp.Utils.AccountFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;

    // WIDGETS
    private BottomNavigationView bottomNavigationView;
    private TextView mTvname;
    private TextView mTvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView = findViewById(R.id.bottomnavigationview);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        openFragment(new HomeFragment());
        setDrawerLayout();
        handlingselection();

        //handling bottom navigation view on click listener

        bottomNavigationView.setOnNavigationItemSelectedListener(navelistener);
        initiaslize();
    }

    private void initiaslize() {

        NavigationView navigationView= findViewById(R.id.nav_view);
        View heder =  navigationView.getHeaderView(0);
        mTvname = heder.findViewById(R.id.header_navname);
        mTvEmail = heder. findViewById(R.id.Header_nav_email);
        FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String name = snapshot.child("name").getValue().toString();
                    String email = snapshot.child("email").getValue().toString();
                    mTvEmail.setText(email);
                    mTvname.setText(name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navelistener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedfragment = null;
            switch (item.getItemId()) {

                case R.id.actionHome:
                    selectedfragment = new HomeFragment();
                    break;
                case R.id.actionMyCart:
                    selectedfragment = new CartFragment();
                    break;
                case R.id.actionFavorites:
                    selectedfragment = new FavoritesFragment();
                    break;
                case R.id.actionNotifications:
                    selectedfragment = new NotificationFragment();
                    break;
                case R.id.actionAccount:
                    selectedfragment = new AccountFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id
                    .fragmentContainer, selectedfragment).commit();
            return true;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.actionLogout) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setTitle(R.string.Sign_out);
            alertDialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    signOut();
                    ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setTitle(R.string.Sign_out);
                    progressDialog.show();
                    Intent intentlogin = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intentlogin);
                    finish();
                }
            });
            alertDialog.show();

        }

        if (id==R.id.actionvendorpage){
            Intent intent= new Intent(getApplicationContext(),VendorPage.class);
            startActivity(intent);
        }
        if(id == R.id.venderRegistration){
            startActivity(new Intent(MainActivity.this,VendorRegistration.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void handlingselection() {
        mNavigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else {

            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);

            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(a);
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
        } else if (id == R.id.nav_myOrders) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new MyOrderaFragment()).commit();
        } else if (id == R.id.nav_trending) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new TrendingFragment()).commit();
        } else if (id == R.id.nav_vendor) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new VendorFragment()).commit();
        } else if (id == R.id.nav_offers) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new OffersFragment()).commit();
        } else if (id == R.id.nav_category) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new CategorisFragment()).commit();
        } else if (id == R.id.nav_profile) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new ProfileFragment()).commit();
        } else if (id == R.id.nav_Help) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new HelpFragment()).commit();
        } else if (id == R.id.nav_contuctUs) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new contuctUsFragment()).commit();
        } else if (id == R.id.actionMyCart) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new CartFragment()).commit();
        }


        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
