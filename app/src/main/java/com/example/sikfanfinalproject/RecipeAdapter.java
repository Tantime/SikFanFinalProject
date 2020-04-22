package com.example.sikfanfinalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> recipeList;
    private ItemClickListener mItemClickListener;

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewName;
        ImageView imageViewImage;

        RecipeViewHolder(ImageView imageView) {
            super(imageView); // what about textView?
            textViewName = textViewName.findViewById(R.id.textView_recipeItem_name);
            imageViewImage = imageView.findViewById(R.id.imageView_recipeItem_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public RecipeAdapter(Context context, List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        ImageView imageView = (ImageView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);

//        RecipeViewHolder vh = new RecipeViewHolder();
        return new RecipeViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.textViewName.setText(recipeList.get(position).getName());
        Picasso.get().load(recipeList.get(position).getImageURL()).into(holder.imageViewImage);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return recipeList.get(id).getName();
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater layoutInflater = getLayoutInflater;
//    }

}
