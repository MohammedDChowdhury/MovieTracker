package com.example.movieapp;

import android.provider.BaseColumns;

public interface Constants  extends BaseColumns {

        public static final String TABLE_NAME = "movies" ;
        //columns in the movie app database
        public static final String MOVIETITLE = "movieTitle" ;
        public static final String YEAR = "year";
        public static final String DIRECTOR = "director";
        public static final String ACTORS = "actors";
        public static final String RATING = "rating";
        public static final String REVIEW = "review";

        public static final String FAVORITE_TABLE_NAME  = "favourite_movies" ;
        //columns in the movie app database
        public static final String FAVORITE_MOVIE_TITLE = "favourite_movieTitle" ;



}
