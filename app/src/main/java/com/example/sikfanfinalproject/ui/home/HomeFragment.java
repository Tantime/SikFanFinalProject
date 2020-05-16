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


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sikfanfinalproject.R;
import com.example.sikfanfinalproject.Recipe;
import com.example.sikfanfinalproject.RecipeAdapter;
import com.example.sikfanfinalproject.RecipeDetailActivity;
import com.example.sikfanfinalproject.RecipeService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment implements RecipeAdapter.ItemClickListener {

    private EditText editTextSearch;
    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
    private ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
    private Recipe recipe1 = new Recipe();
    private Recipe recipe2 = new Recipe();
    private Recipe recipe3 = new Recipe();
    private Recipe recipe4 = new Recipe();
    private Recipe recipe5 = new Recipe();
    private Recipe recipe6 = new Recipe();

    private String search = "";

    public static final String EXTRA_RECIPE = "recipe";

    public static final String TAG = HomeFragment.class.getSimpleName();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        wireWidgets(rootView);
        setListeners();

//        InputStream XmlFileInputStream = getResources().openRawResource(R.raw.recipes); // getting XML
//        String jsonString = readTextFile(XmlFileInputStream);

//        Gson gson = new Gson();
//        Recipe[] recipes = gson.fromJson(jsonString, Recipe[].class);
//        recipeList = Arrays.asList(recipes);
        recipe1.setTitle("curry");
        recipe1.setImage("https://www.jocooks.com/wp-content/uploads/2019/10/coconut-chicken-curry-1-10.jpg");
        recipe2.setTitle("cup noodles");
        recipe2.setImage("https://cdn.shopify.com/s/files/1/0414/0681/products/61PdRnr0QKL._SL1176_1400x.jpg?v=1571438812");
        recipe3.setTitle("dumplings");
        recipe3.setImage("https://www.thespruceeats.com/thmb/uof94YPDmDqP0kAlbi_t04VR47E=/4000x3000/smart/filters:no_upscale()/chinese-pan-fried-dumplings-694499_hero-01-f8489a47cef14c06909edff8c6fa3fa9.jpg");
        recipe4.setTitle("sticky rice");
        recipe4.setImage("https://thewoksoflife.com/wp-content/uploads/2014/04/dim-sum-sticky-rice-2-1-500x375.jpg");
        recipe5.setTitle("taro cake");
        recipe5.setImage("https://previews.123rf.com/images/kenishirotie/kenishirotie1806/kenishirotie180600076/103668763-chinese-style-homemade-pan-fry-yam-taro-cake.jpg");
        recipe6.setTitle("sponge cake");
        recipe6.setImage("https://www.dimsumcentral.com/wp-content/uploads/2014/01/sponge-cake-header-new.jpg");
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
        recipeAdapter = new RecipeAdapter(getActivity(), recipeList, this);
        recipeAdapter.setClickListener(this);
        recyclerView.setAdapter(recipeAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RecipeService.BASE__URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final RecipeService recipeService = retrofit.create(RecipeService.class);

        editTextSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // search for the recipe with api call
                    recipeSearch(recipeService);
                    return true;
                }
                return false;
            }
        });

        return rootView;
    }

    @Override
    public void onItemClick(int position) {
        Log.i("TAG", "You clicked recipe " + recipeAdapter.getItem(position) + ", which is a cell position " + position);
        Intent targetIntent = new Intent(getActivity(), RecipeDetailActivity.class);
        targetIntent.putExtra(EXTRA_RECIPE, recipeList.get(position));
        Log.d(TAG, "onItemClick: " + recipeList.get(position));
        startActivity(targetIntent);
    }

    private void wireWidgets(View rootView) {
        recyclerView = rootView.findViewById(R.id.recyclerView_homeFragment_grid);
        editTextSearch = rootView.findViewById(R.id.editText_homeFragment_search);
    }

    private void setListeners() {
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                search = editTextSearch.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void recipeSearch(RecipeService recipeService) {
        if (search.equals("")) {
            // nothing
        } else {
            final Call<ArrayList<Recipe>> recipeCall = recipeService.getRecipes(search);
            recipeCall.enqueue(new Callback<ArrayList<Recipe>>() {
                @Override
                public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                    recipeList.clear();
                    recipeAdapter.notifyItemRangeRemoved(0, recipeList.size());
                    recipeList = response.body();
//                    for (Recipe recipe : recipeList) {
//                        String content = "Name: " + recipe.getTitle() + "\n" + "Ingredients: " +
//                                recipe.getIngredients() + "\n" + "Steps: " + "\n" +
//                                "imageURL: " + recipe.getImageURL() + "\n\n";
//                    }
                    if (recipeList != null) {
                        recipeAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

//    public String readTextFile(InputStream inputStream) {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//
//        byte buf[] = new byte[1024];
//        int len;
//        try {
//            while ((len = inputStream.read(buf)) != -1) {
//                outputStream.write(buf, 0, len);
//            }
//            outputStream.close();
//            inputStream.close();
//        } catch (IOException e) {
//
//        }
//        return outputStream.toString();
//    }

}

