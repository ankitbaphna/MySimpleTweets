
package com.codepath.apps.mysimpletweets.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;


public class Tweet {

   /* {
        "coordinates": null,
            "truncated": false,
            "created_at": "Tue Aug 28 21:16:23 +0000 2012",
            "favorited": false,
            "id_str": "240558470661799936",
            "in_reply_to_user_id_str": null,
            "extendedEntities": {
        "urls": [

        ],
        "hashtags": [

        ],
        "user_mentions": [

        ]
    },
        "text": "just another test",
            "contributors": null,
            "id": 240558470661799936,
            "retweet_count": 0,
            "in_reply_to_status_id_str": null,
            "geo": null,
            "retweeted": false,
            "in_reply_to_user_id": null,
            "place": null,
            "source": "OAuth Dancer Reborn",
            "user": {
        "name": "OAuth Dancer",
                "profile_sidebar_fill_color": "DDEEF6",
                "profile_background_tile": true,
                "profile_sidebar_border_color": "C0DEED",
                "profile_image_url": "http://a0.twimg.com/profile_images/730275945/oauth-dancer_normal.jpg",
                "created_at": "Wed Mar 03 19:37:35 +0000 2010",
                "location": "San Francisco, CA",
                "follow_request_sent": false,
                "id_str": "119476949",
                "is_translator": false,
                "profile_link_color": "0084B4",
                "extendedEntities": {
            "url": {
                "urls": [
                {
                    "expanded_url": null,
                        "url": "http://bit.ly/oauth-dancer",
                        "indices": [
                    0,
                            26
                    ],
                    "display_url": null
                }
                ]
            },
            "description": null
        },
        "default_profile": false,
                "url": "http://bit.ly/oauth-dancer",
                "contributors_enabled": false,
                "favorite_count": 7,
                "utc_offset": null,
                "profile_image_url_https": "https://si0.twimg.com/profile_images/730275945/oauth-dancer_normal.jpg",
                "id": 119476949,
                "listed_count": 1,
                "profile_use_background_image": true,
                "profile_text_color": "333333",
                "followers_count": 28,
                "lang": "en",
                "protected": false,
                "geo_enabled": true,
                "notifications": false,
                "description": "",
                "profile_background_color": "C0DEED",
                "verified": false,
                "time_zone": null,
                "profile_background_image_url_https": "https://si0.twimg.com/profile_background_images/80151733/oauth-dance.png",
                "statuses_count": 166,
                "profile_background_image_url": "http://a0.twimg.com/profile_background_images/80151733/oauth-dance.png",
                "default_profile_image": false,
                "friends_count": 14,
                "following": false,
                "show_all_inline_media": false,
                "screen_name": "oauth_dancer"
    },
        "in_reply_to_screen_name": null,
            "in_reply_to_status_id": null
    },
    */

    private String body;
    private long id;
    private String createdAt;
    private boolean retweeted;
    private boolean favorited;
    private User user;
    private int favorite_count;
    private int retweet_count;
    private ExtendedEntities extendedEntities;

    public String getBody() {
        return body;
    }

    public long getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public boolean isRetweeted() {
        return retweeted;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public int getFavorite_count() {
        return favorite_count;
    }

    public int getRetweet_count() {
        return retweet_count;
    }

    public User getUser() {
        return user;
    }

    public void setRetweeted(boolean retweeted) {
        this.retweeted = retweeted;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public void setFavorite_count(int favorite_count) {
        this.favorite_count = favorite_count;
    }

    public void setRetweet_count(int retweet_count) {
        this.retweet_count = retweet_count;
    }

    public ExtendedEntities getExtendedEntities() {
        return extendedEntities;
    }

    public static Tweet fromJson(final JSONObject jsonObject){
        Tweet tweet = new Tweet();
        try {
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.body = jsonObject.getString("text");
            tweet.id = jsonObject.getLong("id");
            tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
            tweet.extendedEntities = ExtendedEntities.fromJson(jsonObject.getJSONObject("extended_entities"));
            tweet.favorited = jsonObject.getBoolean("favorited");
            tweet.retweeted = jsonObject.getBoolean("retweeted");
            tweet.retweet_count = jsonObject.getInt("retweet_count");
            tweet.favorite_count = jsonObject.getInt("favorite_count");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tweet;
    }

    public static Collection<Tweet> fromJsonArray(JSONArray response) {
        ArrayList<Tweet> tweets = new ArrayList<>();

        for(int i = 0; i < response.length() ; i++){
            try {
                Tweet tweet = fromJson(response.getJSONObject(i));
                if(tweet != null) {
                    tweets.add(tweet);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }
        return tweets;
    }
}
