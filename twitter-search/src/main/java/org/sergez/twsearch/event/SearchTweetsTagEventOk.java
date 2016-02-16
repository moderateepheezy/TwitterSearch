package org.sergez.twsearch.event;

import org.sergez.twsearch.api.TrendList;
import org.sergez.twsearch.api.TweetTagList;

import java.util.List;

/**
 * Created by simpumind on 2/15/16.
 */
public class SearchTweetsTagEventOk {

    public final List<TrendList> tweetsList;

    public SearchTweetsTagEventOk(List<TrendList> tweets) {
        this.tweetsList = tweets;
    }
}
