package com.example.sikfanfinalproject.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sikfanfinalproject.R;
import com.example.sikfanfinalproject.RecipeListActivity;

public class HomeFragment extends Fragment {

    private static final String EXTRA_RECIPE = "recipe";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecipeListActivity recipeListActivity = new RecipeListActivity();

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView_homeFragment_grid);
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(recipeListActivity, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        wireWidgets(rootView);
        setOnClickListeners();

        return rootView;
    }

    private void wireWidgets(View rootView) {
    }

    private void setOnClickListeners() {
    }

}
