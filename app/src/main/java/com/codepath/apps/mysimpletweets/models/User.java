
package com.codepath.apps.mysimpletweets.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.codepath.apps.mysimpletweets.database.TweetDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.json.JSONException;
import org.json.JSONObject;

@Table(database = TweetDatabase.class)
public class User extends BaseModel implements Parcelable {
    @Column
    private String name;
    @Column
    private String profileName;
    @Column
    private String profileImageUrl;
    @Column
    @PrimaryKey
    private long uid;

    public void setName(String name) {
        this.name = name;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public String getProfileName() {
        return profileName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public long getUid() {
        return uid;
    }

    public static User fromJson(JSONObject jsonObject){
        User user = new User();

        try {
            user.name = jsonObject.getString("name");
            user.profileName = jsonObject.getString("screen_name");
            user.uid = jsonObject.getLong("id");
            user.profileImageUrl = jsonObject.getString("profile_image_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.profileName);
        dest.writeString(this.profileImageUrl);
        dest.writeLong(this.uid);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.name = in.readString();
        this.profileName = in.readString();
        this.profileImageUrl = in.readString();
        this.uid = in.readLong();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
