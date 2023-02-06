package com.example.john_catalog.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.john_catalog.Data.FishRecyclerViewAdapter;

import com.example.john_catalog.Model.Fish;
import com.example.john_catalog.R;
import com.example.john_catalog.Util.Prefs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FishRecyclerViewAdapter fishRecyclerViewAdapter;
    private List<Fish> fishList;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue= Volley.newRequestQueue(this);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fishList=new ArrayList<>();

        Prefs prefs = new Prefs(MainActivity.this);
        String search=prefs.getSearch();
        fishList=getFishes(search);
        fishRecyclerViewAdapter=new FishRecyclerViewAdapter(fishList,this);
//fishRecyclerViewAdapter=new FishRecyclerViewAdapter(fishList, this);
        recyclerView.setAdapter(fishRecyclerViewAdapter);
    }

    public List<Fish> getFishes(String searchTerm)
    {
        fishList.clear();
        String myUrl="https://fish-species.p.rapidapi.com/fish_api/fish/?s=" + searchTerm + "&r=json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(myUrl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                try {
                    JSONArray fishArray=response.getJSONArray("");
                    for(int i=0;i<fishArray.length();i++)
                    {
                        JSONObject fishObj=fishArray.getJSONObject(i);
                        Fish fish = new Fish();
                        fish.setName(fishObj.getString("name"));
                        fish.setBinomial_name(fishObj.getString("binomial_name"));
                        fish.setConservation_status(fishObj.getString("conservation_status"));
                        fish.setImg_src_set(fishObj.getString("1.5x"));
                        fishList.add(fish);
                    }
                    fishRecyclerViewAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                    Map<String, String> params=new HashMap<>();
                params.put("X-RapidAPI-Host","fish-species.p.rapidapi.com");
                params.put("X-RapidAPI-Key","1a3fda9f98msh20063ef4acfb355p1230ffjsn5e63845a94ea");
                return params;
            }
        };

    requestQueue.add(jsonObjectRequest);
    return fishList;
    }
}