package com.codepath.apps.mysimpletweets.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.codepath.apps.mysimpletweets.database.TweetDatabase;
import com.codepath.apps.mysimpletweets.utils.Constants;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by baphna on 10/29/2016.
 */
@Table(database = TweetDatabase.class)
public class ExtendedEntities extends BaseModel implements Parcelable {
    @Column
    @PrimaryKey
    private String mediaUrl;

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mediaUrl);
    }

    public ExtendedEntities() {
    }

    protected ExtendedEntities(Parcel in) {
        this.mediaUrl = in.readString();
    }

    public static final Creator<ExtendedEntities> CREATOR = new Creator<ExtendedEntities>() {
        @Override
        public ExtendedEntities createFromParcel(Parcel source) {
            return new ExtendedEntities(source);
        }

        @Override
        public ExtendedEntities[] newArray(int size) {
            return new ExtendedEntities[size];
        }
    };
}
