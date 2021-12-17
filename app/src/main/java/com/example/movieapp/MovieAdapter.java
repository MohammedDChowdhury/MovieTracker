package com.example.movieapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie> movies;
    private Context context;
    ArrayList<String> favoriteMovies = new ArrayList<>();

    public MovieAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);

        CheckBox checkbox = holder.itemView.findViewById(R.id.checkBox);
        String builder = movie.getMovieTitle() + " " +
                movie.getYear() + " " +
                movie.getDirector() + " " +
                movie.getActors() + " " +
                movie.getRating() + " " +
                movie.getReview() + " ";
        checkbox.setText(builder);

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    favoriteMovies.add(movie.getMovieTitle());
                } else {
                    favoriteMovies.remove(movie.getMovieTitle());
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return movies.size();
    }
}

class MovieViewHolder extends RecyclerView.ViewHolder {
    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}