package com.example.fsmanji.newsfeed;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.fsmanji.domain.model.Article;
import com.example.fsmanji.newsfeed.Presenter.ArticleListPresenter;
import com.example.fsmanji.newsfeed.View.Adapter.ArticleListAdapter;
import com.example.fsmanji.newsfeed.View.ArticleListView;
import com.example.fsmanji.newsfeed.View.ViewState;

import java.util.List;

/**
 * An activity representing a list of Articles. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ArticleDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ArticleListActivity extends AppCompatActivity implements ArticleListView {

    private ArticleListPresenter mPresenter;
    private ArticleListAdapter mAdapter;

    public static Intent getCallingIntent(Context context) {
        Intent i = new Intent(context, ArticleListActivity.class);
        return i;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        View recyclerView = findViewById(R.id.article_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        loadArticles();
    }

    private void loadArticles() {
        if (mPresenter == null) {
            mPresenter = new ArticleListPresenter(this);
        }
        mPresenter.loadArticles();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        mAdapter = new ArticleListAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    /**
     * Callbacks from ArticleListView interface.
     *
     */

    public void showArticleList(List<Article> articleList) {
        mAdapter.updateData(articleList);
    }

    @Override
    public void setViewState(ViewState state) {
        switch (state) {
            case Loading:
            case Retry:
                break;
            case Error:
                break;
            case Empty:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.resume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }
}
