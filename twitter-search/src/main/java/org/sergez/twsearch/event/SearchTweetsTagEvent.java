package org.sergez.twsearch.event;

/**
 * Created by simpumind on 2/15/16.
 */
public class SearchTweetsTagEvent {
    public final String id;
    public final String twitterToken;

    public SearchTweetsTagEvent(String twitterToken, String hashtag) {
        this.id = hashtag;
        this.twitterToken = twitterToken;
    }
}
