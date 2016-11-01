package com.codepath.apps.mysimpletweets.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.application.TwitterApplication;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.codepath.apps.mysimpletweets.network.TwitterClient;
import com.codepath.apps.mysimpletweets.utils.Utils;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * Created by baphna on 10/28/2016.
 */

public class NewTweetFragment extends DialogFragment {

    @BindString(R.string.msg_no_internet)
    String msgNoInternet;

    @BindView(R.id.btnSend)
    Button btnSend;

    @BindView(R.id.ibtnCancel)
    ImageButton ibtnCancel;

    @BindView(R.id.etTweetBody)
    TextInputEditText etTweetBody;

    @BindView(R.id.tvProfileName)
    TextView tvProfileName;
    @BindView(R.id.tvUserName)
    TextView tvUserName;

    public interface NewTweetPostedInterface{
        void onNewTweetPosted(Tweet newTweet);
    }

    public NewTweetFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_new_tweet, container);
        return getActivity().getLayoutInflater().inflate(R.layout.fragment_new_tweet, container);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.ibtnCancel)
    public void cancelClicked(View view){
        getDialog().dismiss();
    }

    @OnClick(R.id.btnSend)
    public void sendTweet(View view){
        if(etTweetBody.getText() != null) {
            TwitterClient client = TwitterApplication.getRestClient();
            client.postToTwitterHomeTimeline(etTweetBody.getText().toString(),
                    -1, new JsonHttpResponseHandler(){
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            super.onSuccess(statusCode, headers, response);
                            NewTweetPostedInterface newTweetPostedInterface = (NewTweetPostedInterface) getActivity();
                            Tweet newTweet = Tweet.fromJson(response);
                            newTweetPostedInterface.onNewTweetPosted(newTweet);
                            dismiss();
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            super.onFailure(statusCode, headers, responseString, throwable);
                            if(!Utils.isNetworkAvailable(getActivity())){
                                Toast.makeText(getActivity(), msgNoInternet, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Failed " + responseString, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
