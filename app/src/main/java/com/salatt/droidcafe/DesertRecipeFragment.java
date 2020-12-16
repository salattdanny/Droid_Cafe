package com.salatt.droidcafe;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.ArrayList;
import java.util.Collections;


public class DesertRecipeFragment extends Fragment {

    private RecyclerView dessertRecyclerView;
    private ArrayList<Recipe> dessertRecipeData;
    private RecipeAdapter dessertAdapter;

    public DesertRecipeFragment() {
        // Required empty public constructor
    }

    private void initializeData() {
        String[] dessertTitles = getResources().getStringArray(R.array.dessert_title);
        String[] dessertDescriptions = getResources().getStringArray(R.array.dessert_description);
        TypedArray dessertImages = getResources().obtainTypedArray(R.array.desserts_images);
        dessertRecipeData.clear();

//        dessertRecipeData.add(1,new Recipe(dessertImages.getResourceId(1,0),dessertTitles[1],dessertDescriptions[1]));
        for (int i=0; i<dessertTitles.length;i++){
            dessertRecipeData.add(i, new Recipe(dessertImages.getResourceId(i,0),dessertTitles[i],dessertDescriptions[i]));
        }

//
        dessertImages.recycle();
        dessertAdapter.notifyDataSetChanged();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_desert_recipe, container, false);

        dessertRecyclerView = rootView.findViewById(R.id.recycler_dessert);
        dessertRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dessertRecipeData = new ArrayList<>();
        dessertAdapter = new RecipeAdapter(dessertRecipeData, getContext());
        dessertRecyclerView.setAdapter(dessertAdapter);
        initializeData();


        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT| ItemTouchHelper.DOWN|ItemTouchHelper.UP,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT| ItemTouchHelper.DOWN|ItemTouchHelper.UP) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = viewHolder.getAdapterPosition();
                Collections.swap(dessertRecipeData, from, to);
                dessertAdapter.notifyItemMoved(from, to);


                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                dessertRecipeData.remove(viewHolder.getAdapterPosition());
                dessertAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

            }
        });

        helper.attachToRecyclerView(dessertRecyclerView);

        return rootView;
    }


}