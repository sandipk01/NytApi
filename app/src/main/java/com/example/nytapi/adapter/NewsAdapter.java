package com.example.nytapi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nytapi.R;
import com.example.nytapi.model.Result;

import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<Result> movies;
    private int rowLayout;
    private Context context;


    public static class NewsViewHolder extends RecyclerView.ViewHolder  {
        LinearLayout moviesLayout;
        TextView Title;
        TextView date;
        TextView Description;

        public NewsViewHolder(View v) {
            super(v);
            moviesLayout =  v.findViewById(R.id.Linear_layout);
            Title =  v.findViewById(R.id.title);
            date =  v.findViewById(R.id.date);
             Description = v.findViewById(R.id.description);
        }


    }

    public NewsAdapter(List<Result> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new NewsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final NewsViewHolder holder, final int position) {
            holder.Title.setText(movies.get(position).getTitle());
            holder.date.setText(movies.get(position).getCreatedDate());
            holder.Description.setText(movies.get(position).getAbstract());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}