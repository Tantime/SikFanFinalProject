package com.example.sikfanfinalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private ArrayList<Recipe> recipeList;
    private LayoutInflater mInflater;
    private ItemClickListener mItemClickListener;

    private static final String EXTRA_RECIPE = "recipe";

    public RecipeAdapter(Context context, ArrayList<Recipe> recipeList, ItemClickListener itemClickListener) {
        this.mInflater = LayoutInflater.from(context);
        this.recipeList = recipeList;
        this.mItemClickListener = itemClickListener;
    }

    @Override
    @NonNull
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.textViewName.setText(recipeList.get(position).getTitle());
        Picasso.get().load(recipeList.get(position).getImage()).into(holder.imageViewImage);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewName;
        ImageView imageViewImage;
        ItemClickListener itemClickListener;

        RecipeViewHolder(View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textView_recipeItem_name);
            imageViewImage = itemView.findViewById(R.id.imageView_recipeItem_image);
            this.itemClickListener = itemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return recipeList.get(id).getTitle();
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(int position);
    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater layoutInflater = getLayoutInflater;
//    }

    public void recipeById(RecipeService recipeService) {
        if (editTextSearch.getText().toString().equals("")) {
            // nothing
        } else {
            final Call<ArrayList<Recipe>> recipeCall = recipeService.getRecipes(editTextSearch.getText().toString());
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

}
