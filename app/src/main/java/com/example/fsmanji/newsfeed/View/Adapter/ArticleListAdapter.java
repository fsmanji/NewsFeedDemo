package com.example.fsmanji.newsfeed.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fsmanji.domain.model.Article;
import com.example.fsmanji.newsfeed.Navigator.Navigator;
import com.example.fsmanji.newsfeed.R;

import java.util.List;

/**
 * Created by fsmanji on 2/20/17.
 */

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder> {
    private List<Article> mArticles;

    public void updateData(List<Article> items) {
        mArticles = items;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.article = mArticles.get(position);
        holder.title.setText(holder.article.getTitle());
        Glide.with(holder.view.getContext()).load(holder.article.getUrlToImage()).into(holder.image);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Navigator.navigateToArticleDetailView(context, holder.article.getId());

            }
        });
    }

    @Override
    public int getItemCount() {
        return mArticles == null? 0: mArticles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView title;
        public final ImageView image;
        public Article article;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            title = (TextView) view.findViewById(R.id.article_title);
            image = (ImageView) view.findViewById(R.id.article_image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + title.getText() + "'";
        }
    }
}
