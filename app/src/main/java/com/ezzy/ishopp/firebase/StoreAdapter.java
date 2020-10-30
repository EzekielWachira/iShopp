package com.ezzy.ishopp.firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ezzy.ishopp.R;
import com.ezzy.ishopp.models.Item;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class StoreAdapter extends FirebaseRecyclerAdapter<Item, StoreAdapter.StoreViewHolder> {

    Context context;

    public StoreAdapter(@NonNull FirebaseRecyclerOptions<Item> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull StoreViewHolder holder, int position, @NonNull Item model) {
            holder.titleTextView.setText(model.getName());
            holder.descriptionTextView.setText(model.getDescription());
            holder.priceTextView.setText(model.getPrice());
            Glide.with(context)
                    .load(model.getImage_url())
                    .centerCrop()
                    .into(holder.itemImageView);
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_store, parent, false);
        return new StoreViewHolder(view);
    }

    class StoreViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView, descriptionTextView, priceTextView;
        ImageView itemImageView;
        ImageButton editButton, deleteButton;

        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.itemTitle);
            descriptionTextView = itemView.findViewById(R.id.itemDescription);
            priceTextView = itemView.findViewById(R.id.itemPrice);
            itemImageView = itemView.findViewById(R.id.itemImage);
            editButton = itemView.findViewById(R.id.editImageButton);
            deleteButton = itemView.findViewById(R.id.deleteImageButton);
        }
    }
}
