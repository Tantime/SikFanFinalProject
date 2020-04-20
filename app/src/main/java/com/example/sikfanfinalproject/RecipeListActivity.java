package com.example.sikfanfinalproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sikfanfinalproject.ui.home.HomeFragment;

import java.util.List;

public class RecipeListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public static final String EXTRA_RECIPE = "recipe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_homeFragment_grid);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // using a grid layout manager
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // specify an adapter (see also next example)
        mAdapter = new RecipeAdapter(recipeList);
        recyclerView.setAdapter(mAdapter);

        wireWidgets();
        setListeners();

        HomeFragment homeFragment = new HomeFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.navigation_home, homeFragment);
    }

    private void wireWidgets() {
        recyclerView = findViewById(R.id.recyclerView_homeFragment_grid);
    }

    private void setListeners() {
//        recyclerView
    }

//    private class RecipeAdapter extends ArrayAdapter<Recipe> {
//
//        private List<Recipe> recipeList;
//
//        public RecipeAdapter(List<Recipe> recipeList) {
//            super(RecipeListActivity.this, -1, recipeList);
//            this.recipeList = recipeList;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            LayoutInflater inflater = getLayoutInflater();
//            if(convertView == null) {
//                convertView = inflater.inflate(R.layout.item_recipe, parent, false);
//            }
//        }
//    }

    public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {

        private List<Recipe> recipeList;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public static class MyViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView textView;

            public MyViewHolder(TextView v) {
                super(v);
                textView = v;
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public RecipeAdapter(List<Recipe> recipeList) {
            this.recipeList = recipeList;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public RecipeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
            // create a new view
            TextView v = (TextView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.my_text_view, parent, false);
        ...
            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.textView.setText(recipeList.get(position).getName());

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return recipeList.size();
        }
    }

}
