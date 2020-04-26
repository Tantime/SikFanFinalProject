package com.example.sikfanfinalproject.ui.home;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.sikfanfinalproject.R;
//import com.example.sikfanfinalproject.RecipeListActivity;
//
//public class HomeFragment extends Fragment {
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        RecipeListActivity recipeListActivity = new RecipeListActivity();
//
//        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
//
//        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView_homeFragment_grid);
//        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(recipeListActivity, 2);
//        recyclerView.setLayoutManager(gridLayoutManager);
//
//        wireWidgets(rootView);
//        setOnClickListeners();
//
//        return rootView;
//    }
//
//    private void wireWidgets(View rootView) {
//    }
//
//    private void setOnClickListeners() {
//    }
//
//}


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sikfanfinalproject.R;
import com.example.sikfanfinalproject.Recipe;
import com.example.sikfanfinalproject.RecipeAdapter;
import com.example.sikfanfinalproject.RecipeListFragment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements RecipeAdapter.ItemClickListener {

    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
    private List<Recipe> recipeList = new ArrayList<Recipe>();
    private Recipe recipe1 = new Recipe();
    private Recipe recipe2 = new Recipe();
    private Recipe recipe3 = new Recipe();
    private Recipe recipe4 = new Recipe();
    private Recipe recipe5 = new Recipe();
    private Recipe recipe6 = new Recipe();

    public static final String EXTRA_RECIPE = "recipe";

    public static final String TAG = RecipeListFragment.class.getSimpleName();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        wireWidgets(rootView);
        setListeners();

        InputStream XmlFileInputStream = getResources().openRawResource(R.raw.recipes); // getting XML
        String jsonString = readTextFile(XmlFileInputStream);

//        Gson gson = new Gson();
//        Recipe[] recipes = gson.fromJson(jsonString, Recipe[].class);
//        recipeList = Arrays.asList(recipes);
        recipe1.setName("curry");
        recipe1.setImageURL("https://www.jocooks.com/wp-content/uploads/2019/10/coconut-chicken-curry-1-10.jpg");
        recipe2.setName("cup noodles");
        recipe2.setImageURL("https://cdn.shopify.com/s/files/1/0414/0681/products/61PdRnr0QKL._SL1176_1400x.jpg?v=1571438812");
        recipe3.setName("dumplings");
        recipe3.setImageURL("https://www.thespruceeats.com/thmb/uof94YPDmDqP0kAlbi_t04VR47E=/4000x3000/smart/filters:no_upscale()/chinese-pan-fried-dumplings-694499_hero-01-f8489a47cef14c06909edff8c6fa3fa9.jpg");
        recipe4.setName("sticky rice");
        recipe4.setImageURL("https://thewoksoflife.com/wp-content/uploads/2014/04/dim-sum-sticky-rice-2-1-500x375.jpg");
        recipe5.setName("taro cake");
        recipe5.setImageURL("https://previews.123rf.com/images/kenishirotie/kenishirotie1806/kenishirotie180600076/103668763-chinese-style-homemade-pan-fry-yam-taro-cake.jpg");
        recipe6.setName("sponge cake");
        recipe6.setImageURL("https://www.dimsumcentral.com/wp-content/uploads/2014/01/sponge-cake-header-new.jpg");
        recipeList.add(recipe1);
        recipeList.add(recipe2);
        recipeList.add(recipe3);
        recipeList.add(recipe4);
        recipeList.add(recipe5);
        recipeList.add(recipe6);
        Log.d(TAG, "onCreate: " + recipeList.toString());

//        recyclerView.setHasFixedSize(true);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        recipeAdapter = new RecipeAdapter(getActivity(), recipeList);
        recipeAdapter.setClickListener(this);
        recyclerView.setAdapter(recipeAdapter);

        return rootView;
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.i("TAG", "You clicked number " + recipeAdapter.getItem(position) + ", which is a cell position " + position);
    }

    private void wireWidgets(View rootView) {
        recyclerView = rootView.findViewById(R.id.recyclerView_homeFragment_grid);
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

