package com.codepath.apps.mysimpletweets.models;

import android.util.Log;

import com.codepath.apps.mysimpletweets.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by baphna on 10/29/2016.
 */

public class ExtendedEntities {
    private String mediaUrl;

    public String getMediaUrl() {
        return mediaUrl;
    }

    public static ExtendedEntities fromJson(JSONObject jsonObject){
        ExtendedEntities extendedEntities = new ExtendedEntities();

        try {
            JSONObject mediaObject = (JSONObject) jsonObject.getJSONArray("media").get(0);
            extendedEntities.mediaUrl = mediaObject.getString("media_url");
            Log.d(Constants.TAG, "media url is " + extendedEntities.mediaUrl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return extendedEntities;
    }
}
