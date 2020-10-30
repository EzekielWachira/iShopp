package com.ezzy.ishopp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ezzy.ishopp.models.Item;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


public class StoreFragment extends Fragment {
    private View itemViewFragment;
    private String currenttuserid;
    private RecyclerView mRecyclerViewItem;
    private DatabaseReference mItemreference;


    public StoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        itemViewFragment = inflater.inflate(R.layout.fragment_store, container, false);
        mRecyclerViewItem = itemViewFragment.findViewById(R.id.storeRecyclerView);
        mRecyclerViewItem.setLayoutManager(new LinearLayoutManager(getContext()));
        currenttuserid = FirebaseAuth.getInstance().getUid();
        mItemreference = FirebaseDatabase.getInstance().getReference().child("store").child(currenttuserid).child("items");


        return itemViewFragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        //creating a firebase recycler adapter

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Item>()
                .setQuery(mItemreference, Item.class)
                .build();

        FirebaseRecyclerAdapter<Item, itemVh> adapter = new FirebaseRecyclerAdapter<Item, itemVh>(options) {


            @Override
            protected void onBindViewHolder(@NonNull final itemVh holder, int position, @NonNull Item model) {
                String itemid = getRef(position).getKey();
                mItemreference.child(itemid).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild("image_url")) {
                            String itemname = snapshot.child("name").getValue().toString();
                            String itemdescriptuion = snapshot.child("description").getValue().toString();
                            String itemPrice = snapshot.child("price").getValue().toString();
                            final String itemimage = snapshot.child("image_url").getValue().toString();
                            holder.mItemtitle.setText(itemname);
                            holder.mItemdescription.setText(itemdescriptuion);
                            holder.mItemPrice.setText("Ksh: " + itemPrice);
                            Picasso.get().load(itemimage).fit().centerInside().networkPolicy(NetworkPolicy.OFFLINE)
                                    .placeholder(R.drawable.ic__person).into(holder.mItemImage, new Callback() {
                                @Override
                                public void onSuccess() {

                                }

                                @Override
                                public void onError(Exception e) {
                                    Picasso.get().load(itemimage).fit().centerInside()
                                            .placeholder(R.drawable.ic__person).into(holder.mItemImage);
                                }
                            });
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @NonNull
            @Override
            public itemVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemtest, parent, false);
                return new itemVh(view);
            }
        };


        mRecyclerViewItem.setAdapter(adapter);
        adapter.startListening();

    }


    public static class itemVh extends RecyclerView.ViewHolder {

        private TextView mItemtitle, mItemdescription, mItemPrice;
        private ImageView mItemImage;

        public itemVh(@NonNull View itemView) {
            super(itemView);
            mItemtitle = itemView.findViewById(R.id.testtv);
            mItemdescription = itemView.findViewById(R.id.itemDescription);
            mItemPrice = itemView.findViewById(R.id.itemPrice);
            mItemImage = itemView.findViewById(R.id.itemImageView);
        }
    }


}

