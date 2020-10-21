package com.bca.marvelcharacters.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.marvelcharacters.R;
import com.squareup.picasso.Picasso;

public class HeroesHolder extends RecyclerView.ViewHolder {

    ImageView iv_heroes;
    TextView tv_id, tv_name;
    View parent;

    public HeroesHolder(@NonNull View itemView) {
        super(itemView);
        iv_heroes = itemView.findViewById(R.id.imageViewHeroes);
        tv_id = itemView.findViewById(R.id.textViewIdHeroes);
        tv_name = itemView.findViewById(R.id.textViewNameHeroes);
        parent = itemView;
    }

    public void setData(String id, String name, String imagePath){
        tv_id.setText(id);
        tv_name.setText(name);
        Picasso.get().load(imagePath).into(iv_heroes);
    }


}
