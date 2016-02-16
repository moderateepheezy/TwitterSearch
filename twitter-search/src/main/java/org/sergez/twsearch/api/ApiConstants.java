package org.sergez.twsearch.api;

/**
 * @author Sergii Zhuk
 *         Date: 24.06.2014
 *         Time: 22:28
 */
public class ApiConstants {

 	public final static String CONSUMER_KEY = "wFHuJnU6kqMOA240XPoHTqgEy"; // HIDDEN, please obtain your one on twitter developers

	public	final static String CONSUMER_SECRET = "THx2Q8yWOJU7736E0U7Iif9ceK3uhTRvaPL2tNJai1eL6ZTnl5";  // HIDDEN, please obtain your one on twitter developers

	public final static String TWITTER_SEARCH_URL = "https://api.twitter.com";

	public static final String BEARER_TOKEN_CREDENTIALS = CONSUMER_KEY + ":" + CONSUMER_SECRET;

	public final static String TWITTER_HASHTAG_SEARCH_CODE = "/1.1/search/tweets.json";

	public final static String TWITTER_TREND_SEARCH_CODE = "/1.1/trends/place.json";

}
