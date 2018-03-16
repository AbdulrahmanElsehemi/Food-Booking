package com.example.abdulrahman.order.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abdulrahman.order.InterFace.itemClickListener;
import com.example.abdulrahman.order.R;



public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtFoodName,foodPrice;
    public ImageView imageView;
    public  ImageView fav_image;
    private itemClickListener clickListener;

    public void setClickListener(itemClickListener clickListener) {
        this.clickListener = clickListener;
    }
    public FoodViewHolder(View itemView) {
        super(itemView);
        txtFoodName=(TextView)itemView.findViewById(R.id.food_name);
        imageView=(ImageView)itemView.findViewById(R.id.food_image);
        fav_image=(ImageView)itemView.findViewById(R.id.fav);
       // foodPrice=(TextView)itemView.findViewById(R.id.food_priceee);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        clickListener.onClick(view,getAdapterPosition(),false);
    }
}
