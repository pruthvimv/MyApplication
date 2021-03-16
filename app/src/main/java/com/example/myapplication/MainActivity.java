package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.VoiceInteractor;
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

public class MainActivity extends Activity implements ExampleAdapter.onItemClickListner {

    private RecyclerView mRecyclerView;
    private ExampleAdapter mExmapleAdapter;
    private ArrayList<ExampleItem> mExampleItems;
    private RequestQueue mRequestQueue;
    public static final String EXTRA_URL = "imageurl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleItems = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        PareseJson();
    }

    private void PareseJson() {
        String url = "https://pixabay.com/api/?key=20704079-ab2debd69aaf3007d65d20027&q=yellow+flowers&image_type=photo";
        JsonObjectRequest json = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("hits");

                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject hit = jsonArray.getJSONObject(i);
                        String name= hit.getString("user");
                        String url = hit.getString("webformatURL");
                        int likes = hit.getInt("likes");
                        mExampleItems.add(new ExampleItem(url, name, likes));

                    }

                    mExmapleAdapter = new ExampleAdapter(MainActivity.this, mExampleItems);
                    mRecyclerView.setAdapter(mExmapleAdapter);
                    mExmapleAdapter.setOnItemClickListener(MainActivity.this);
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
        mRequestQueue.add(json);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        ExampleItem item = mExampleItems.get(position);
        intent.putExtra(EXTRA_URL, item.getmImageUrl());
        startActivity(intent);
    }
}