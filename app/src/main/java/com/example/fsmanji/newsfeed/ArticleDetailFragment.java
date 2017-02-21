package com.example.fsmanji.newsfeed;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fsmanji.domain.model.Article;
import com.example.fsmanji.newsfeed.Presenter.ArticleDetailPresenter;
import com.example.fsmanji.newsfeed.View.ArticleDetailView;
import com.example.fsmanji.newsfeed.View.ViewState;

/**
 * A fragment representing a single Article detail screen.
 * This fragment is either contained in a {@link ArticleListActivity}
 * in two-pane mode (on tablets) or a {@link ArticleDetailActivity}
 * on handsets.
 */
public class ArticleDetailFragment extends Fragment implements ArticleDetailView {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    private ArticleDetailPresenter mPresenter;
    private String mArticleId;
    private TextView mTitleView;
    private TextView mDescriptionView;
    private ImageView mImageView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ArticleDetailFragment() {
        mPresenter = new ArticleDetailPresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mArticleId = getArguments().getString(ARG_ITEM_ID);
            mPresenter.getArticle(mArticleId);
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mArticleId);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.article_detail, container, false);
        mTitleView = (TextView)rootView.findViewById(R.id.article_title);
        mDescriptionView = (TextView)rootView.findViewById(R.id.article_detail);
        mImageView = (ImageView)rootView.findViewById(R.id.article_image);

        return rootView;
    }

    public void showArticle(Article article) {
        if (article != null) {
            mTitleView.setText(article.getTitle());
            mDescriptionView.setText(article.getDescription());
            Glide.with(getContext()).load(article.getUrlToImage()).into(mImageView);
        }
    }

    @Override
    public void setViewState(ViewState state) {

    }
}
