package com.bca.marvelcharacters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bca.marvelcharacters.adapter.HeroesAdapter;
import com.bca.marvelcharacters.delegate.Callback;
import com.bca.marvelcharacters.model.Hero;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Callback {

    String TAG = "MainActivity";
    RecyclerView obj_recyclerview;
    HeroesAdapter heroesAdapter = new HeroesAdapter();
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        obj_recyclerview = findViewById(R.id.recyclerViewHeroes);
        obj_recyclerview.setLayoutManager(linearLayoutManager);
        obj_recyclerview.setHasFixedSize(true);

        heroesAdapter.setCallback(this);
        obj_recyclerview.setAdapter(heroesAdapter);
        initDataCharacters();

    }

    void initDataCharacters() {
        String publicKey = "9ab2c8402160bc333e74316594606fec";
        String privateKey = "b2cc3224adc1b0c8adbc065b36917d71254766ae";
        Long tsLong = System.currentTimeMillis() / 1000;
        String ts = tsLong.toString();
        String concate = ts + privateKey + publicKey;
        String hashed = getMd5(concate);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://gateway.marvel.com/v1/public/characters?ts=" + ts + "&apikey=" + publicKey + "&hash=" + hashed + "&limit=100";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    // Parse JSON here
                    Log.d(TAG, "initDataCharacters: " + response);
                    List<Hero> heroes = new ArrayList<>();

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                        JSONArray jsonArray = jsonObject1.getJSONArray("results");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                            String id = jsonObject2.getString("id");
                            String name = jsonObject2.getString("name");
                            String description = jsonObject2.getString("description");

                            JSONObject jsonObject3 = jsonObject2.getJSONObject("thumbnail");
                            String path = jsonObject3.getString("path");
                            String extension = jsonObject3.getString("extension");
                            String imagePath = path + "." + extension;

                            StringBuilder stringBuilder = new StringBuilder(imagePath);
                            stringBuilder.insert(4, "s");

                            heroes.add(new Hero(id, name, stringBuilder.toString(), description));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    heroesAdapter.setDataHeroes(heroes);
                    heroesAdapter.notifyDataSetChanged();

                }, error -> Log.d(TAG, "That didn't work! "));

        queue.add(stringRequest);

    }

    public static String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void itemPressed(String id, String name, String imagePath, String description) {
        Intent intent = new Intent(MainActivity.this, DetailHeroActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("imagePath", imagePath);
        intent.putExtra("description", description);
        startActivity(intent);
    }
}