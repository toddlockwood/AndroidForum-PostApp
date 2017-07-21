package com.example.arek.hateapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class addMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);

        final EditText msg = (EditText) findViewById(R.id.txtMessage);
        final Button send = (Button) findViewById(R.id.btnSend);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String post = msg.getText().toString();
                String user_id = Integer.toString(GlobalParams.getUser_id());

                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success){
                                Intent intent = new Intent(addMessage.this, PostActivity.class);
                                addMessage.this.startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(addMessage.this);
                                builder.setMessage("Send Failed")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };

                PostRequest postRequest = new PostRequest(user_id, post, responseListener);
                RequestQueue queue = Volley.newRequestQueue(addMessage.this);
                queue.add(postRequest);
            }
        });
    }
}