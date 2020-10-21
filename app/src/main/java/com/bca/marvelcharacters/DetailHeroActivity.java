package com.bca.marvelcharacters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailHeroActivity extends AppCompatActivity {

    TextView tv_title, tv_description;
    ImageView iv_heroes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hero);
        tv_title = findViewById(R.id.textViewTitleDetail);
        tv_description = findViewById(R.id.textViewDescriptionDetail);
        iv_heroes = findViewById(R.id.imageViewHeroesDetail);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String imagePath = intent.getStringExtra("imagePath");
        String description = intent.getStringExtra("description");

        tv_title.setText(name);
        tv_description.setText(description);
        Picasso.get().load(imagePath).into(iv_heroes);

    }
}