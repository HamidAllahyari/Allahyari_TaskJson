package com.android.train.task.json;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.train.task.json.Properti.Properties;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MoveiActivity extends AppCompatActivity {
    private static final String TAG = "MoveiActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //final ArrayList<String> movies = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movei);
        Intent intent = getIntent();
        String imdbId = intent.getStringExtra("id");
        String address = "https://www.omdbapi.com/?i="+imdbId+"&apikey=70ad462a";
        final TextView txtDetailTitle = findViewById(R.id.txtDetailTitle);
        final TextView txtYear = findViewById(R.id.txtYear);
        final TextView txtDirector = findViewById(R.id.txtDirector);
        final TextView txtActors = findViewById(R.id.txtActors);
        final ImageView imgPoster = findViewById(R.id.imgPoster);
        final TextView txtGenre = findViewById(R.id.txtGenre);
        final TextView txtCountry = findViewById(R.id.txtCountry);
        final TextView txtLanguage = findViewById(R.id.txtLanguage);

        AsyncHttpClient client = new AsyncHttpClient();



        client.get(address, new  JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                ////////DataBase////////////
                TestDataBase db=new TestDataBase(MoveiActivity.this,"MovieDB",null,1);
                ///////////////////////////

                Gson gson = new Gson();
                Properties properti = gson.fromJson(response.toString(), Properties.class);

                txtDetailTitle.setText(properti.getTitle());
                txtYear.setText("Yeare: "+properti.getYear());
                txtDirector.setText("Director: "+properti.getDirector());
                txtActors.setText("Actors: "+properti.getActors());
                txtGenre.setText("Genre: "+properti.getGenre());
                txtCountry.setText("Country: " + properti.getCountry());
                txtLanguage.setText("Language: " + properti.getLanguage());

                String imageUrl = properti.getPoster();
                Picasso.get().load(imageUrl).into(imgPoster);

                ///////////InsertDB////////////////
                db.insertFilm(properti.getTitle(),properti.getYear(),properti.getDirector(),properti.getGenre()
                ,properti.getCountry(),properti.getLanguage(),properti.getActors());
                ///////////////////////////////////
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });


    }
}
