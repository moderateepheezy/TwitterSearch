package org.sergez.twsearch.api;

/**
 * Created by simpumind on 2/16/16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trend {

    @SerializedName("tweet_volume")
    @Expose
    public Integer tweetVolume;
    @SerializedName("events")
    @Expose
    public Object events;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("promoted_content")
    @Expose
    public Object promotedContent;
    @SerializedName("query")
    @Expose
    public String query;
    @SerializedName("url")
    @Expose
    public String url;

    /**
     *
     * @return
     * The tweetVolume
     */
    public Integer getTweetVolume() {
        return tweetVolume;
    }

    /**
     *
     * @param tweetVolume
     * The tweet_volume
     */
    public void setTweetVolume(Integer tweetVolume) {
        this.tweetVolume = tweetVolume;
    }

    /**
     *
     * @return
     * The events
     */
    public Object getEvents() {
        return events;
    }

    /**
     *
     * @param events
     * The events
     */
    public void setEvents(Object events) {
        this.events = events;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The promotedContent
     */
    public Object getPromotedContent() {
        return promotedContent;
    }

    /**
     *
     * @param promotedContent
     * The promoted_content
     */
    public void setPromotedContent(Object promotedContent) {
        this.promotedContent = promotedContent;
    }

    /**
     *
     * @return
     * The query
     */
    public String getQuery() {
        return query;
    }

    /**
     *
     * @param query
     * The query
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
