package com.ezzy.ishopp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ezzy.ishopp.firebase.StoreAdapter;
import com.ezzy.ishopp.models.Item;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class StoreFragment extends Fragment {

    private RecyclerView storeRecyclerview;
    StoreAdapter adapter;
    private View mView;
    DatabaseReference mDatabaseReference, itemreference;
    FirebaseDatabase mDatabase;
    List<Item> itemList;

    private String mAuth;


    public StoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        storeRecyclerview = view.findViewById(R.id.storeRecyclerView);
        storeRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        itemList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabaseReference = mDatabase.getReference();
        itemreference = FirebaseDatabase.getInstance().getReference().child("store").child(mAuth).child("items");
//        mDatabaseReference.child("store")
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                            itemList = (List<Item>) dataSnapshot.getValue(User.class);
//
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        makeToast("Error retrieving data from database");
//                    }
//                });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Item> options = new FirebaseRecyclerOptions.Builder<Item>().setQuery(itemreference, Item.class).build();
        FirebaseRecyclerAdapter<Item, itemVh> adapter = new FirebaseRecyclerAdapter<Item, itemVh>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final itemVh holder, int position, @NonNull final Item model) {
                String mMItemid = getRef(position).getKey();
                assert mMItemid != null;
                itemreference.child(mMItemid).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {

                            String ItemName = snapshot.child("name").getValue().toString();
                            holder.mItemtitle.setText(model.getName());
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
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_store, parent, false);
                return new itemVh(view);
            }
        };
        storeRecyclerview.setAdapter(adapter);
        adapter.startListening();
    }

    public static class itemVh extends RecyclerView.ViewHolder {

        private TextView mItemtitle, mItemdescription, mItemPrice;
        private ImageView mItemImage;

        public itemVh(@NonNull View itemView) {
            super(itemView);
            mItemtitle = itemView.findViewById(R.id.itemTitle);
            mItemdescription = itemView.findViewById(R.id.itemDescription);
            mItemPrice = itemView.findViewById(R.id.itemPrice);
            mItemImage = itemView.findViewById(R.id.itemImageView);
        }
    }

    private void makeToast(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


}