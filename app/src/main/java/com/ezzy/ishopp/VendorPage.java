package com.ezzy.ishopp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.ezzy.ishopp.Utils.addItem;
import com.ezzy.ishopp.Utils.vendorPaggerAdapter;
import com.ezzy.ishopp.models.Vendor;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class VendorPage extends AppCompatActivity {

    private static final String[] tabTitles = new String[]{"Store", "Customers", "Orders"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_page);
        Toolbar toolbar = findViewById(R.id.vendorpageToolBar);
        toolbar.setTitle("Your page");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent.hasExtra("vendor")){
            Vendor mVendor = intent.getParcelableExtra("vendor");
            actionBar.setTitle(mVendor.getBusiness_name());
        }

        init();
    }

    private void init() {
        TabLayout tabLayout = findViewById(R.id.vendorpageTablayout);
        ViewPager2 vendorViewPagger = findViewById(R.id.vendorviewpagger2);

        vendorViewPagger.setAdapter(new vendorPaggerAdapter(this));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, vendorViewPagger, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabTitles[position]);
            }
        });
        tabLayoutMediator.attach();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.vendorpage_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if ( id==R.id.action_vendor_add){
            openAdd();
        }
        return super.onOptionsItemSelected(item);
    }

    private void openAdd() {
addItem addItem = new addItem();
addItem.show(getSupportFragmentManager(),"Item");
    }
}