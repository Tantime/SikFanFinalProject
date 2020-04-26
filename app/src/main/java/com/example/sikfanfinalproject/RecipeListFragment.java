package com.example.sikfanfinalproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class RecipeListFragment extends Fragment implements RecipeAdapter.ItemClickListener {

    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
    private List<Recipe> recipeList;

    public static final String EXTRA_RECIPE = "recipe";

    public static final String TAG = RecipeListFragment.class.getSimpleName();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InputStream XmlFileInputStream = getResources().openRawResource(R.raw.recipes); // getting XML
        String jsonString = readTextFile(XmlFileInputStream);

        Gson gson = new Gson();
//        Type recipes = new TypeToken<List<Recipe>>(){}.getType();
//        List<Recipe> recipeList = Arrays.asList(gson.fromJson(jsonString, Recipe[].class));
        Recipe[] recipes = gson.fromJson(jsonString, Recipe[].class);
        recipeList = Arrays.asList(recipes);
        Log.d(TAG, "onCreate: " + recipeList.toString());

        recyclerView.setHasFixedSize(true);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        recipeAdapter = new RecipeAdapter(getActivity(), recipeList);
        recipeAdapter.setClickListener(this);
        recyclerView.setAdapter(recipeAdapter);

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        wireWidgets(rootView);
        setListeners();

        return rootView;
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.i("TAG", "You clicked number " + recipeAdapter.getItem(position) + ", which is a cell position " + position);
    }

    private void wireWidgets(View rootView) {
        recyclerView = getActivity().findViewById(R.id.recyclerView_homeFragment_grid);
    }

    private void setListeners() {
    }

    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }

}
