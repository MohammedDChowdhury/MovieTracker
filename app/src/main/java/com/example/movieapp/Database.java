package com.example.movieapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;
import static com.example.movieapp.Constants.ACTORS;
import static com.example.movieapp.Constants.DIRECTOR;
import static com.example.movieapp.Constants.FAVORITE_MOVIE_TITLE;
import static com.example.movieapp.Constants.FAVORITE_TABLE_NAME;
import static com.example.movieapp.Constants.MOVIETITLE;
import static com.example.movieapp.Constants.RATING;
import static com.example.movieapp.Constants.REVIEW;
import static com.example.movieapp.Constants.TABLE_NAME;
import static com.example.movieapp.Constants.YEAR;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "movies.db"; //interface
    private static final int DATABASE_VERSION = 2;




    public Database(Context ctx) { //constructor
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
             + MOVIETITLE + " TEXT NOT NULL, " + YEAR + " INTEGER, " + DIRECTOR + " TEXT NOT NULL, " + ACTORS + " TEXT NOT NULL, " + RATING + " INTEGER, " + REVIEW + " TEXT NOT NULL);");
        db.execSQL("CREATE TABLE " + FAVORITE_TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FAVORITE_MOVIE_TITLE + " TEXT NOT NULL);");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void addMovie(String titleOfMovie ,Integer yearOfMovie,String directorOfMovie,String actorAndActressesOfMovie,Integer ratingOfMovie,String reviewOfMovie) {
        /* Insert a new record into the Movie data
source. You would do something similar
for delete and update. */
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MOVIETITLE, titleOfMovie);
        values.put(YEAR, yearOfMovie);
        values.put(DIRECTOR, directorOfMovie);
        values.put(ACTORS, actorAndActressesOfMovie);
        values.put(RATING, ratingOfMovie);
        values.put(REVIEW, reviewOfMovie);
        db.insertOrThrow(TABLE_NAME, null, values);


    }
    public void addFavouriteMovie(String titleOfMovie) {
        /* Insert a new record into the Movie data
source. You would do something similar
for delete and update. */
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FAVORITE_MOVIE_TITLE, titleOfMovie);
        db.insertOrThrow(FAVORITE_TABLE_NAME, null, values);


    }

    public Cursor getMovies() {
        /* Perform a managed query. The Activity will
handle closing and re-querying the cursor
when needed. */
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =  db.query(TABLE_NAME,null,null,null,null,null,MOVIETITLE + " ASC");
        return cursor;
    }

    public Cursor getFavouriteMovies() {
        /* Perform a managed query. The Activity will
handle closing and re-querying the cursor
when needed. */
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =  db.query(FAVORITE_TABLE_NAME,null,null,null,null,null,FAVORITE_MOVIE_TITLE+ " ASC");
        return cursor;
    }
    public List<Movie> showMovies(Cursor cursor) {
        //Stuff them all into a big string
        StringBuilder builder = new StringBuilder("Saved movies:\n"); //title
        List<Movie> movies = new ArrayList<Movie>();
        while(cursor.moveToNext()){
            /* Could use getColumnIndexOrThrow() to
get indexes */
            long id = cursor.getLong(0);
            long time = cursor.getLong(1);
            String title = cursor.getString(2);    //each item has a column, like (title, director and the others).
            int movieTitleIndex = cursor.getColumnIndex(MOVIETITLE); //the index is how you get the column each item is.
            String movieTitle = cursor.getString(movieTitleIndex);
            int directorOfMovieIndex = cursor.getColumnIndex(DIRECTOR);
            String directorOfMovie = cursor.getString(directorOfMovieIndex);
            int actorsOfMovieIndex = cursor.getColumnIndex(ACTORS);
            String actorsOfMovie = cursor.getString(actorsOfMovieIndex);
            int ratingOfMovieIndex = cursor.getColumnIndex(RATING);
            String ratingOfMovie = cursor.getString(ratingOfMovieIndex);
            int reviewOfMovieIndex = cursor.getColumnIndex(REVIEW);
            String reviewOfMovie = cursor.getString(reviewOfMovieIndex);
            builder.append("\n");
            builder.append(" ID: " + id).append(" | ");
            builder.append("Movie Title: " + movieTitle + " | ");
            builder.append("Year: " + title + " | ");
            builder.append("Director: " + directorOfMovie + " | "); //that new line is to add new line after the last column for the next row
            builder.append("Actors: " + actorsOfMovie + " | ");
            builder.append("Ratings: " + ratingOfMovie + " | ");
            builder.append("Review: " + reviewOfMovie + "\n" + "\n" + "------------------------------------------------------------------------------------------------------");
            movies.add(new Movie(2,movieTitle,title,directorOfMovie,actorsOfMovie,ratingOfMovie,reviewOfMovie));





        }

        cursor.close();
        return movies;

    }
    public List<Movie> showFavouriteMovies(Cursor cursor) {
        //Stuff them all into a big string
        StringBuilder builder = new StringBuilder("Saved movies:\n"); //title
        List<Movie> movies = new ArrayList<Movie>();
        while(cursor.moveToNext()){
            /* Could use getColumnIndexOrThrow() to
get indexes */
      //      long id = cursor.getLong(0);
     //       long time = cursor.getLong(1);
      //      String title = cursor.getString(2);    //each item has a column, like (title, director and the others).
            int movieTitleIndex = cursor.getColumnIndex(FAVORITE_MOVIE_TITLE); //the index is how you get the column each item is.
            String movieTitle = cursor.getString(movieTitleIndex);
            System.out.println(movieTitle);

       /*     builder.append("\n");
            builder.append(" ID: " + id).append(" | ");
            builder.append("Movie Title: " + movieTitle + " | ");
            builder.append("Year: " + title + " | ");
            builder.append("Director: " + null + " | "); //that new line is to add new line after the last column for the next row
            builder.append("Actors: " + null + " | ");
            builder.append("Ratings: " + null + " | ");
            builder.append("Review: " + null + "\n" + "\n" + "------------------------------------------------------------------------------------------------------"); */
            movies.add(new Movie(2,movieTitle,"","","","",""));





        }

        cursor.close();
        return movies;

    }



}
