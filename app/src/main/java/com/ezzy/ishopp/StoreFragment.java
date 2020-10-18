package com.ezzy.ishopp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ezzy.ishopp.firebase.StoreAdapter;
import com.ezzy.ishopp.models.Item;
import com.ezzy.ishopp.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreFragment extends Fragment {

    private RecyclerView storeRecyclerview;
    StoreAdapter adapter;
    DatabaseReference mDatabaseReference;
    FirebaseDatabase mDatabase;
    List<Item> itemList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StoreFragment() {
        // Required empty public constructor
    }

    public static StoreFragment newInstance(String param1, String param2) {
        StoreFragment fragment = new StoreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        storeRecyclerview = view.findViewById(R.id.storeRecyclerView);
//        itemList = new ArrayList<>();
//        mDatabase = FirebaseDatabase.getInstance();
//        mDatabaseReference = mDatabase.getReference();
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

    private void makeToast(String message){
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}