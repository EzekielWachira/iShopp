package com.ezzy.ishopp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

import com.ezzy.ishopp.Utils.OnBoardItem;
import com.ezzy.ishopp.Utils.OnBoardViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class OnboardActivity extends AppCompatActivity {

    // Widgets
//    private ViewPager2 onBoardViewPager;
    private ViewPager onBoardViewPager;
    private TabLayout tabIndicator;
    private ImageButton prevButton, nextButton;
    private Button getStartedButton;

    Animation buttonAnimation;

    // Items
    private OnBoardViewPagerAdapter onBoardViewPagerAdapter;
    List<OnBoardItem> onBoardItemList;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_onboard);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        getSupportActionBar().hide();

        if (restorePreferenceData()){
            Intent loginIntent = new Intent(OnboardActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }

        onBoardViewPager = findViewById(R.id.introViewPager);
        tabIndicator = findViewById(R.id.activity_indcator);
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);
        getStartedButton = findViewById(R.id.getStartedButton);

        buttonAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);

        onBoardItemList = new ArrayList<>();
        onBoardItemList.add(new OnBoardItem(R.drawable.onboard_1_image,
                getResources().getString(R.string.onboard_1_description)
        ));
        onBoardItemList.add(new OnBoardItem(R.drawable.onboard_2_image,
                getResources().getString(R.string.onboard_2_description)
        ));
        onBoardItemList.add(new OnBoardItem(R.drawable.onboard_3_image,
                getResources().getString(R.string.onboard_3_description)
        ));
        onBoardItemList.add(new OnBoardItem(R.drawable.onboard_4_image,
                getResources().getString(R.string.onboard_4_description)
        ));

        onBoardViewPager.setAdapter(createOnBoardAdapter(onBoardItemList));
//        new TabLayoutMediator(tabIndicator, onBoardViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
//            @Override
//            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//
//            }
//        }).attach();
        tabIndicator.setupWithViewPager(onBoardViewPager);

        tabIndicator.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == onBoardItemList.size() - 1){
                    loadLastScreen();
                }
                if (tab.getPosition() < onBoardItemList.size() - 1){
                    nextButton.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = onBoardViewPager.getCurrentItem();
                if (position < onBoardItemList.size()){
                    position++;
                    onBoardViewPager.setCurrentItem(position);
                }
                if (position == onBoardItemList.size() - 1){
                    loadLastScreen();
                }
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = onBoardViewPager.getCurrentItem();
                if (position > 1){
//                    showPrevButton();
                }
                if (position == onBoardItemList.size() - 1){
                    loadLastScreen();
                }
            }
        });

        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(OnboardActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                savePreferenceData();
                finish();
            }
        });

    }

    private void showPrevButton() {
        prevButton.setVisibility(View.VISIBLE);
    }

    private OnBoardViewPagerAdapter createOnBoardAdapter(List<OnBoardItem> item){
        OnBoardViewPagerAdapter adapter = new OnBoardViewPagerAdapter(this, item);
        return adapter;
    }

    private void loadLastScreen(){
        nextButton.setVisibility(View.INVISIBLE);
        prevButton.setVisibility(View.INVISIBLE);
        getStartedButton.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);

        getStartedButton.setAnimation(buttonAnimation);
    }

    private void savePreferenceData() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isOnBoardOpened", true);
        editor.commit();
    }

    private boolean restorePreferenceData(){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("prefs", MODE_PRIVATE);
        Boolean isOnBoardOpened = preferences.getBoolean("isOnBoardOpened", false);
        return  isOnBoardOpened;
    }
}