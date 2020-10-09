package com.ezzy.ishopp.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.ezzy.ishopp.R;

import java.util.List;

public class OnBoardViewPagerAdapter extends PagerAdapter {

    Context context;
    List<OnBoardItem> onBoardItemList;

    public OnBoardViewPagerAdapter(Context context, List<OnBoardItem> onBoardItemList) {
        this.context = context;
        this.onBoardItemList = onBoardItemList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.layout_onboard_screen, container,false);
        ImageView onboardImageView = layoutScreen.findViewById(R.id.onboardImageView);
        TextView onboardDescription = layoutScreen.findViewById(R.id.onboardDescription);
        onboardImageView.setImageResource(onBoardItemList.get(position).getImage());
        onboardDescription.setText(onBoardItemList.get(position).getDescription());
        container.addView(layoutScreen);
        return layoutScreen;
    }

    @Override
    public int getCount() {
        return onBoardItemList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View)object);
    }
}
