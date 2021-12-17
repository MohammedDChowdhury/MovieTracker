package com.example.movieapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Movie;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static android.provider.BaseColumns._ID;
import static android.widget.Toast.makeText;
import static com.example.movieapp.Constants.ACTORS;
import static com.example.movieapp.Constants.DIRECTOR;
import static com.example.movieapp.Constants.MOVIETITLE;
import static com.example.movieapp.Constants.RATING;
import static com.example.movieapp.Constants.REVIEW;
import static com.example.movieapp.Constants.TABLE_NAME;
import static com.example.movieapp.Constants.YEAR;


public class RegisterMovie extends AppCompatActivity {
// variables being set for each text inputs so stores information that we click/edit
    //having these specified by their types is a lot easier
    private static String[] FROM = { _ID, MOVIETITLE, YEAR, DIRECTOR, ACTORS, RATING, REVIEW,};
    private static String ORDER_BY = MOVIETITLE + "  DESC";
    private Database movies;
    String titleOfMovie;
    int yearOfMovie;
    String directorOfMovie;
    String actorAndActressesOfMovie;
    int ratingOfMovie;
    String reviewOfMovie;
 //able to access these within my methods
    EditText titleOfMovieInput, yearOfMovieInput, directorOfMovieInput, actorAndActressesOfMovieInput, ratingOfMovieInput, reviewOfMovieInput;
    Button buttonSave;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registermovie); //setting this class to be assigned the register movie xml page
        Toast mainToast= makeText(getApplicationContext(),"Welcome to the registration page",Toast.LENGTH_SHORT);
        mainToast.show();

        titleOfMovieInput = findViewById(R.id.et_title); //assigning the editText to the title xml
        yearOfMovieInput = findViewById(R.id.et_year); //assigning the editText to the year xml
        directorOfMovieInput = findViewById(R.id.et_director); //linking the editText with the director xml
        actorAndActressesOfMovieInput = findViewById(R.id.et_actorActress);
        ratingOfMovieInput = findViewById(R.id.et_rating); //assigning it to the xml
        reviewOfMovieInput = findViewById(R.id.et_review);
        buttonSave = findViewById(R.id.bt_save); //initialising this save button

        movies = new Database(this);
       // try {
        //    addMovie("Hello, Android!");
         //   Cursor cursor = getMovies();
          //  showMovies(cursor);
       // }
       // finally {
      //      movies.close();
       // }



      /*  buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterMovie.this,ViewContents.class);
                startActivity(intent);
            }
        }); */
        buttonSave.setOnClickListener(new View.OnClickListener() { //save click listener
            @Override
            public void onClick(View v) {
                titleOfMovie = titleOfMovieInput.getText().toString();
                yearOfMovie = Integer.valueOf(yearOfMovieInput.getText().toString());
                directorOfMovie = directorOfMovieInput.getText().toString();
                actorAndActressesOfMovie = actorAndActressesOfMovieInput.getText().toString();
                ratingOfMovie = Integer.valueOf(ratingOfMovieInput.getText().toString());
                reviewOfMovie = reviewOfMovieInput.getText().toString();




                if(titleOfMovie.isEmpty() == false && directorOfMovie.isEmpty()  == false && actorAndActressesOfMovie.isEmpty()==false && reviewOfMovie.isEmpty() == false
                        && checkYearOfMovieInput() && checkRatingsRange() == true){ //checking all conditions before we save it


                    movies.addMovie(titleOfMovie ,yearOfMovie,directorOfMovie,actorAndActressesOfMovie,ratingOfMovie,reviewOfMovie);
                    Toast toastSuccess= makeText(getApplicationContext(),"Successful",(Toast.LENGTH_SHORT));

                    toastSuccess.show();

                    System.out.println("This is successful");


                }
                else{
                    System.out.println("Something went wrong");
                    Toast toastFail = makeText(getApplicationContext(),"Something went wrong. Make sure all fields are filled up" +
                            " or that the year is above 1985 and that Rating is within 1-10",(Toast.LENGTH_LONG));
                    toastFail.show();
                    System.out.println("This is unsuccessful");
                }







            }



         //   private void SaveDataForIntegers(int yearOfMovie, int ratingOfMovie){

           // }
        });



    } //these 2 boolean METHODS below are to make sure that the year and the ratings are valid and meet their conditions. They are to check if they are true.




    // If true then they go to the next stage of my if statement in the on click method to see whether or not all fields are full.
    //if those conditions meet as true then we save the data into the DB.

        public boolean checkYearOfMovieInput(){ //checks whether or not the value is higher than 1895
            yearOfMovie = Integer.valueOf(yearOfMovieInput.getText().toString()); //assigning it as a string as user inputs strings

           if(yearOfMovie > 1895){
               System.out.println("This value is fine");
               return true;
           }
           else{
               System.out.println("The year cannot be below 1895");

               return false;


           }

        }
        public boolean checkRatingsRange() { //checks whether or not the range is within 1-10 for the rating
            ratingOfMovie = Integer.valueOf(ratingOfMovieInput.getText().toString());

            if (ratingOfMovie >= 1 && ratingOfMovie <= 10) {
                System.out.println("This value is fine");
                return true;
            } else {
                System.out.println("The rating range must be within 1-10");

                return false;
            }


        }








    }


