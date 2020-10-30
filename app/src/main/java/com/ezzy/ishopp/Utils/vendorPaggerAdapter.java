package com.ezzy.ishopp.Utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ezzy.ishopp.CustomerFragment;
import com.ezzy.ishopp.OrdersFragment;
import com.ezzy.ishopp.StoreFragment;

public class vendorPaggerAdapter extends FragmentStateAdapter {
    public vendorPaggerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return  new StoreFragment();
            case  1: return  new CustomerFragment();
            case  2: return new OrdersFragment();
            default: return new StoreFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
