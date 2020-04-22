package com.example.sikfanfinalproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sikfanfinalproject.ui.home.HomeFragment;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class RecipeListActivity extends AppCompatActivity implements RecipeAdapter.ItemClickListener {

    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
    private List<Recipe> recipeList;

    public static final String EXTRA_RECIPE = "recipe";

    public static final String TAG = RecipeListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        wireWidgets();
        setListeners();

//        HomeFragment homeFragment = new HomeFragment();
//        FragmentManager manager = getSupportFragmentManager();
//        manager.beginTransaction().replace(R.id.navigation_home, homeFragment);

        InputStream XmlFileInputStream = getResources().openRawResource(R.raw.recipes); // getting XML
        String jsonString = readTextFile(XmlFileInputStream);

        Gson gson = new Gson();
        Recipe[] recipes = gson.fromJson(jsonString, Recipe[].class);
        recipeList = Arrays.asList(recipes);
        Log.d(TAG, "onCreate: " + recipeList.toString());

        recyclerView.setHasFixedSize(true);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        recipeAdapter = new RecipeAdapter(this, recipeList);
        recipeAdapter.setClickListener(this);
        recyclerView.setAdapter(recipeAdapter);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        HomeFragment fragment = new HomeFragment();
        fragmentTransaction.add(R.id.nav_host_fragment, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onItemClick(View view, int position) {
        Log.i("TAG", "You clicked number " + recipeAdapter.getItem(position) + ", which is a cell position " + position);
    }

    private void wireWidgets() {
        recyclerView = findViewById(R.id.recyclerView_homeFragment_grid);
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
