package com.example.moviedirectory.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.moviedirectory.Model.Movie;
import com.example.moviedirectory.R;
import com.example.moviedirectory.Util.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieDetailsActivity extends AppCompatActivity {

    private Movie movie;
    private TextView movieTitle, movieYear, director, actors;
    private TextView genre, rating, writers, plot, boxOffice, runtime, rated;
    private ImageView iv_background, movieImage;

    private RequestQueue queue;
    private String movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        queue = Volley.newRequestQueue(this);

        movie = (Movie) getIntent().getSerializableExtra("movie");
        movieId = movie.getImdbID();
        setUpUI();
        getMovieDetails(movieId);

        FloatingActionButton fab = findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MovieDetailsActivity.this, AppInfo.class));
            }
        });
    }

    private void setUpUI() {
        movieTitle = (TextView) findViewById(R.id.movieTitleIDDets);
        movieImage = (ImageView) findViewById(R.id.movieImageIDDets);
        movieYear = (TextView) findViewById(R.id.movieReleaseIDDets);
        director = (TextView) findViewById(R.id.movieDirectorIDDets);
        genre = (TextView) findViewById(R.id.movieGenreIDDets);
        rating = (TextView) findViewById(R.id.movieRatingIDDets);
        writers = (TextView) findViewById(R.id.writersDet);
        plot = (TextView) findViewById(R.id.plotDet);
        boxOffice = (TextView) findViewById(R.id.boxOfficeDet);
        runtime = (TextView) findViewById(R.id.movieRuntimeIDDets);
        actors = (TextView) findViewById(R.id.actorsDet);
        iv_background = (ImageView) findViewById(R.id.iv_background);
        rated = (TextView) findViewById(R.id.movieRatedIDDets);
    }

    private void getMovieDetails(String Id) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constants.URL_ID + Id,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.has("imdbRating"))
                        rating.setText("Imdb - "+response.getString("imdbRating"));
                    else
                        rating.setText("Ratings - N/A");

                        genre.setText("Genre - "+response.getString("Genre"));
                        movieTitle.setText(response.getString("Title"));
                        movieYear.setText("Released - "+response.getString("Year"));
                        director.setText("Director - " + response.getString("Director"));
                        writers.setText("Writers - " + response.getString("Writer"));
                        plot.setText("Plot - " + response.getString("Plot"));
                        runtime.setText("Runtime - " + response.getString("Runtime"));
                        actors.setText("Actors - " + response.getString("Actors"));
                        rated.setText("Rated - "+response.getString("Rated"));

                        Picasso.get().load(response.getString("Poster"))
                                .into(movieImage);

                        Bitmap image=((BitmapDrawable)movieImage.getDrawable()).getBitmap();
                        iv_background.setImageBitmap(image);

                        boxOffice.setText("Box Office - "+response.getString("BoxOffice"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error:",error.getMessage());
            }
        });
        queue.add(jsonObjectRequest);
    }

    public static class AppInfo extends AppCompatActivity {

        private TextView info,github,linkedIn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_app_info);

            info = (TextView) findViewById(R.id.appDetails);
            github = (TextView) findViewById(R.id.github);
            linkedIn= (TextView) findViewById(R.id.linkedIn);

            info.setMovementMethod(LinkMovementMethod.getInstance());
            github.setMovementMethod(LinkMovementMethod.getInstance());
            linkedIn.setMovementMethod(LinkMovementMethod.getInstance());

        }
    }
}