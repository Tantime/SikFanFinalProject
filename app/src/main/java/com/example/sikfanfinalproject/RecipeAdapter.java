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
    private LayoutInflater mInflater;
    private ItemClickListener mItemClickListener;

    public RecipeAdapter(Context context, List<Recipe> recipeList) {
        this.mInflater = LayoutInflater.from(context);
        this.recipeList = recipeList;
    }

    @Override
    @NonNull
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view);
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

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewName;
        ImageView imageViewImage;

        RecipeViewHolder(View itemView) {
            super(itemView); // what about textView?
            textViewName = itemView.findViewById(R.id.textView_recipeItem_name);
            imageViewImage = itemView.findViewById(R.id.imageView_recipeItem_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return recipeList.get(id).getName();
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
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
