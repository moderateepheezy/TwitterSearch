package org.sergez.twsearch;

import android.app.ListFragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import org.sergez.twsearch.api.Trend;
import org.sergez.twsearch.api.TrendList;
import org.sergez.twsearch.event.SearchTweetsTagEventOk;
import org.sergez.twsearch.api.TweetTagList;
import org.sergez.twsearch.event.SearchTweetsEventFailed;
import org.sergez.twsearch.event.SearchTweetsTagEvent;
import org.sergez.twsearch.event.TwitterGetTokenEvent;
import org.sergez.twsearch.event.TwitterGetTokenEventFailed;
import org.sergez.twsearch.event.TwitterGetTokenEventOk;
import org.sergez.twsearch.util.BusProvider;
import org.sergez.twsearch.util.PrefsController;
import org.sergez.twsearch.util.TweetTagAdapter;

import java.util.ArrayList;
import java.util.List;

import static org.sergez.twsearch.util.Util.makeToast;

/**
 * Created by simpumind on 2/15/16.
 */
public class HashFragment extends ListFragment {

    private static final String TAG = HashFragment.class.getName();
    private Bus mBus;
    private String request;

    private TweetTagAdapter brandAdapter;

    private TextView textResult;

    public static final String ARG_SEARCH_REQUEST = "request";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_tweets, container, false);
        brandAdapter = new TweetTagAdapter(getActivity(), new ArrayList());

       // Log.e("Valeu for name", new TrendList().getTrends().get(0).getName());
        setListAdapter(brandAdapter);
        request = getArguments().getString(ARG_SEARCH_REQUEST);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        getBus().register(this);
        if (TextUtils.isEmpty(PrefsController.getAccessToken(getActivity()))) {
            getBus().post(new TwitterGetTokenEvent());
        } else {
            String token = PrefsController.getAccessToken(getActivity());
            getBus().post(new SearchTweetsTagEvent(token, request));
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        getBus().unregister(this);
    }

    @Subscribe
    public void onTwitterGetTokenOk(TwitterGetTokenEventOk event) {
        getBus().post(new SearchTweetsTagEvent(PrefsController.getAccessToken(getActivity()), request));
    }

    @Subscribe
    public void onTwitterGetTokenFailed(TwitterGetTokenEventFailed event) {
        makeToast(getActivity(), "Failed to get token");
    }

    @Subscribe
    public void onSearchTweetsEventOk(final SearchTweetsTagEventOk event) {
       brandAdapter.setTweetTagList(event.tweetsList);
        for(int i = 0; i < event.tweetsList.size(); i++)
        Log.d("Trying", event.tweetsList.get(i).getTrends().get(i).getName() + "  " + brandAdapter.getCount());

        brandAdapter.notifyDataSetChanged();
    }

    @Subscribe
    public void onSearchTweetsEventFailed(SearchTweetsEventFailed event) {
        makeToast(getActivity(), "Search of tweets failed");
    }


    // TODO migrate to DI
    private Bus getBus() {
        if (mBus == null) {
            mBus = BusProvider.getInstance();
        }
        return mBus;
    }

    public void setBus(Bus bus) {
        mBus = bus;
    }
}
