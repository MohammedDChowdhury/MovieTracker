package com.example.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.example.movieapp.Constants.MOVIETITLE;

public class DisplayedMovies extends AppCompatActivity {
        CheckBox checkBoxTesting;
        Button buttonSaving;
    @Override
    public void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaymovies);
        // Display on the screen
        RecyclerView tvDisplayMovies = (RecyclerView) findViewById(R.id.movie_list);
        buttonSaving = (Button) findViewById(R.id.saveFavoriteBtn);
        Database databaseInstance = new Database(this);
        List<Movie> movies = databaseInstance.showMovies(databaseInstance.getMovies());
        MovieAdapter movieAdapter = new MovieAdapter(movies,getApplicationContext());
        tvDisplayMovies.setAdapter(movieAdapter);
        buttonSaving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (String movie : movieAdapter.favoriteMovies){
                    databaseInstance.addFavouriteMovie(movie);
                }
                System.out.println("Hello just testing");
                Intent intentFavourite = new Intent(DisplayedMovies.this,Favourites.class);
                startActivity(intentFavourite);
                System.out.println("Hello just testing again");

            }
        });

    //    tvDisplayMovies.setText(databaseInstance.showMovies(databaseInstance.getMovies()));

      //  CheckBox tvCheckboxTest = (CheckBox) findViewById(R.id.checkbox_Test);
      //  tvCheckboxTest.addChildrenForAccessibility();





    }



}
