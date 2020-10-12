package com.ezzy.ishopp.Utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ezzy.ishopp.BeautyFragment;
import com.ezzy.ishopp.ClothesFragment;
//import com.ezzy.ishopp.ComputersFragment;
import com.ezzy.ishopp.ComputerFragment;
import com.ezzy.ishopp.ComputersFragment;
import com.ezzy.ishopp.ElectronicsFragment;
import com.ezzy.ishopp.FoodFragment;
import com.ezzy.ishopp.PhonesFragment;
import com.ezzy.ishopp.ShoesFragment;

public class MainViewPagerAdapter extends FragmentStateAdapter {

    private static final int tabsNumber = 7;

    public MainViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

//    public MainViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
//        super(fragmentActivity);
//    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new FoodFragment();
            case 1:
                return new ClothesFragment();
            case 2:
                return new BeautyFragment();
            case 3:
                return new ShoesFragment();
            case 4:
                return new ElectronicsFragment();
            case 5:
                return new PhonesFragment();
            case 6:
                return new ComputerFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return tabsNumber;
    }
}
