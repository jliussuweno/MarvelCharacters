package com.bca.marvelcharacters.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.marvelcharacters.R;
import com.bca.marvelcharacters.delegate.Callback;
import com.bca.marvelcharacters.holder.HeroesHolder;
import com.bca.marvelcharacters.model.Hero;

import java.util.ArrayList;
import java.util.List;

public class HeroesAdapter extends RecyclerView.Adapter {

    private List<Hero> arrHero = new ArrayList<>();
    private Callback callback = null;

    public void setDataHeroes(List<Hero> heroes){
        arrHero = heroes;
    }

    public void setCallback(Callback callbackDelegate){
        callback = callbackDelegate;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HeroesHolder tmpHolder = null;
        View tmpView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_heroes, parent, false);
        tmpHolder = new HeroesHolder(tmpView);
        return tmpHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Hero hero = arrHero.get(position);

        ((HeroesHolder)holder).setData(hero.getId(), hero.getName(),hero.getImagePath());
        ((HeroesHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.itemPressed(hero.getId(), hero.getName(), hero.getImagePath(), hero.getDescription());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrHero.size();
    }
}
