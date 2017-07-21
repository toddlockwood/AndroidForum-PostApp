package com.example.arek.hateapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class showMessages extends AppCompatActivity {

    ArrayList<mPost> dataModels;
    ListView listView;
    private listViewAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_messages);
        listView=(ListView)findViewById(R.id.myListView);
        dataModels= new ArrayList<>();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    JSONArray data = jsonResponse.getJSONArray(0);

                    for(int i=0; i<20; i++)
                    {
                        JSONObject obj = data.getJSONObject(i);
                        String login = obj.getString("login");
                        String post = obj.getString("post");
                        dataModels.add(new mPost(login,post));
                    }
                    adapter= new listViewAdapter(dataModels,getApplicationContext());
                    listView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        ShowMessagesRequest showMessagesRequest = new ShowMessagesRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(showMessages.this);
        queue.add(showMessagesRequest);






}
}
