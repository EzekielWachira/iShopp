package com.ezzy.ishopp.Utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VerticalItemDecorator extends RecyclerView.ItemDecoration {

    private final int VERTICAL_SPACE_HEIGHT;

    public VerticalItemDecorator(int VERTICAL_SPACE_HEIGHT) {
        this.VERTICAL_SPACE_HEIGHT = VERTICAL_SPACE_HEIGHT;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        outRect.bottom = VERTICAL_SPACE_HEIGHT;
        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1){
            outRect.bottom = VERTICAL_SPACE_HEIGHT;
        }
    }
}
