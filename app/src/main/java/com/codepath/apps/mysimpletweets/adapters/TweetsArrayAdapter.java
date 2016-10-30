package com.codepath.apps.mysimpletweets.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.models.ExtendedEntities;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.codepath.apps.mysimpletweets.utils.Utils;

import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by baphna on 10/27/2016.
 */

public class TweetsArrayAdapter extends RecyclerView.Adapter<TweetsArrayAdapter.ViewHolder> {

    private Context mContext;
    private List<Tweet> tweets;

    @BindDrawable(R.drawable.ic_star_golden_48dp)
    Drawable icGoldStar;

    public TweetsArrayAdapter(Context mContext, List<Tweet> tweets) {
        this.mContext = mContext;
        this.tweets = tweets;
    }

    @Override
    public TweetsArrayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);
        holder.tvUserHandle.setText("@"+tweet.getUser().getProfileName());
        holder.tvTweetBody.setText(tweet.getBody());
        holder.btnRetweet.setText(Integer.toString(tweet.getRetweet_count()));
        holder.btnFav.setText(Integer.toString(tweet.getFavorite_count()));
        holder.tvUserName.setText(tweet.getUser().getName());
        holder.tvTweetTime.setText(Utils.getRelativeTimeAgo(tweet.getCreatedAt()));

        if(!tweet.isRetweeted()){
            holder.bntRetweetBy.setVisibility(View.GONE);
        } else{
            //TODO
        }

        if(tweet.isFavorited()){
            holder.btnFav.setCompoundDrawables(icGoldStar, null, null, null);
        }

        String profileImageUrl  = tweet.getUser().getProfileImageUrl();
        ExtendedEntities entities = tweet.getExtendedEntities();
        if(entities != null) {
            String mediaUrl = entities.getMediaUrl();
            Glide.with(mContext).load(mediaUrl).into(holder.ivTweetImage);
        } else {
            holder.ivTweetImage.setVisibility(View.GONE);
        }
        //TODO: round it
        Glide.with(mContext).load(profileImageUrl)
                .into(holder.ivUserImage);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvTweetBody)
        TextView tvTweetBody;
        @BindView(R.id.tvUserName)
        TextView tvUserName;
        @BindView(R.id.tvUserHandle)
        TextView tvUserHandle;
        @BindView(R.id.tvTweetTime)
        TextView tvTweetTime;

        @BindView(R.id.ivUserImage)
        ImageView ivUserImage;
        @BindView(R.id.ivTweetImage)
        ImageView ivTweetImage;

        @BindView(R.id.btnFav)
        Button btnFav;
        @BindView(R.id.btnReply)
        Button btnReply;
        @BindView(R.id.btnRetweet)
        Button btnRetweet;
        @BindView(R.id.bntRetweetBy)
        Button bntRetweetBy;

        @BindDrawable(R.drawable.ic_star_golden_48dp)
        Drawable ic_star_golden;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.btnFav)
        public void FavClicked(View view){
            //TODO
            if(tweets.get(getLayoutPosition()).isFavorited()){
                btnFav.setCompoundDrawables(mContext.getDrawable(R.drawable.ic_star_border_grey_48dp), null, null, null);
            } else {
                btnFav.setCompoundDrawables(mContext.getDrawable(R.drawable.ic_star_golden_48dp), null, null, null);
            }
            Snackbar.make(view, R.string.msg_no_internet, Snackbar.LENGTH_SHORT).show();
        }

        @OnClick(R.id.btnReply)
        public void ReplyClicked(View view){
            //TODO
            //getLayoutPosition()
            Snackbar.make(view, R.string.msg_no_internet, Snackbar.LENGTH_SHORT).show();
        }

        @OnClick(R.id.btnRetweet)
        public void ReTweetClicked(View view){
            //TODO
            Snackbar.make(view, R.string.msg_no_internet, Snackbar.LENGTH_SHORT).show();
        }
    }
}
