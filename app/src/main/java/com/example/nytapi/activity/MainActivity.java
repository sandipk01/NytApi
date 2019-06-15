package com.example.nytapi.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nytapi.R;
import com.example.nytapi.adapter.NewsAdapter;
import com.example.nytapi.model.Example;
import com.example.nytapi.model.Result;
import com.example.nytapi.rest.ApiClient;
import com.example.nytapi.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();


    private final static String API_KEY = "uAWJxi7r0cJCAg66K55l1OlCofyJfHGQ";
    ProgressBar Progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Progressbar=findViewById(R.id.Progressbar);
        Progressbar.setVisibility(View.VISIBLE);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY from themoviedb.org first!", Toast.LENGTH_LONG).show();
            return;
        }

        final RecyclerView recyclerView = findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<Example> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                int statusCode = response.code();
                List<Result> movies = response.body().getResults();
                recyclerView.setAdapter(new NewsAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
                Progressbar.setVisibility(View.GONE);
            }


            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}

