package com.example.movieapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Favourites extends AppCompatActivity {


    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_favourites);   // Display on the screen
        RecyclerView tvDisplayFavouriteMovies = (RecyclerView) findViewById(R.id.favourite_movie_list);
        Database databaseInstance = new Database(this);
        List<Movie> showFavouriteMovies = databaseInstance.showFavouriteMovies(databaseInstance.getFavouriteMovies());
        tvDisplayFavouriteMovies.setAdapter(new MovieAdapter(showFavouriteMovies,getApplicationContext()));
    }
}
