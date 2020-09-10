package com.imdigitalashish.imagesearchapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemAdapter.OnItemClickListener {


    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_CREATOR = "creatorName";
    public static final String EXTRA_LIKES = "likeCount";


    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private ArrayList<OneItem> oneItems;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        oneItems = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);
        parseJSON();

    }

    private void parseJSON() {

        String url = "https://pixabay.com/api/?key=18254299-ce34038ed1f7ac36e83c8fd7b&q="+getIntent().getStringExtra("SEARCH")+"&image_type=photo&pretty=true";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String creatorName = hit.getString("user");
                                String imageUrl = hit.getString("webformatURL");
                                int likes = hit.getInt("likes");
                                oneItems.add(new OneItem(imageUrl, creatorName, likes));
                            }

                            itemAdapter = new ItemAdapter(MainActivity.this, oneItems);
                            recyclerView.setAdapter(itemAdapter);
                            itemAdapter.setOnItemClickListener(MainActivity.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);

    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        OneItem oneItem = oneItems.get(position);
        detailIntent.putExtra(EXTRA_URL, oneItem.getmImageUrl());
        detailIntent.putExtra(EXTRA_CREATOR, oneItem.getmCreator());
        detailIntent.putExtra(EXTRA_LIKES, oneItem.getmLikes());
        startActivity(detailIntent);

    }
}