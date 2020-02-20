package com.android.train.task.json;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.train.task.json.Movie_Json.List;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Json2RecylcerAcc extends AppCompatActivity {

    private static final String TAG = "Movei";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_to_recylcer);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

        String address = "https://www.omdbapi.com/?s="+title+"&apikey=70ad462a";

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(address, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                Gson gson = new Gson();
                List list = gson.fromJson(response.toString(), List.class);

                if (list.getSearch() != null) {

                    RecyclerView moviesRecycler = findViewById(R.id.moviesRecycler);

                    Adapter adapter = new Adapter(list.getSearch());
                    moviesRecycler.setAdapter(adapter);
                    moviesRecycler.setLayoutManager(new LinearLayoutManager(Json2RecylcerAcc.this
                            , RecyclerView.VERTICAL, false));
                }
                else
                {
                    Toast.makeText(Json2RecylcerAcc.this, "No found", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
