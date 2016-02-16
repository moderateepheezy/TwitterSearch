package org.sergez.twsearch.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by simpumind on 2/15/16.
 */
public class TweetTag {

    @SerializedName("name")
    public String nameTag;


    @SerializedName("tweet_volume")
    public String tweetVolume;

    @SerializedName("query")
    public String queryTag;

    @SerializedName("url")
    public String tweetUrl;

    @Override
    public String  toString(){
        return nameTag  ;
    }
}
