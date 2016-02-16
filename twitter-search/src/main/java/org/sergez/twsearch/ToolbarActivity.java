package org.sergez.twsearch;

import android.os.Bundle;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lapism.searchview.adapter.SearchAdapter;
import com.lapism.searchview.adapter.SearchItem;
import com.lapism.searchview.history.SearchHistoryTable;
import com.lapism.searchview.view.SearchCodes;
import com.lapism.searchview.view.SearchView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import org.sergez.twsearch.event.SearchTweetsTagEventOk;
import org.sergez.twsearch.event.SearchTweetsEventFailed;
import org.sergez.twsearch.event.TwitterGetTokenEvent;
import org.sergez.twsearch.event.TwitterGetTokenEventFailed;
import org.sergez.twsearch.event.TwitterGetTokenEventOk;
import org.sergez.twsearch.util.BusProvider;
import org.sergez.twsearch.util.PrefsController;
import org.sergez.twsearch.util.TweetTagAdapter;

import java.util.ArrayList;
import java.util.List;

import static org.sergez.twsearch.util.Util.makeToast;

public class ToolbarActivity extends BaseActivity{

    private SearchHistoryTable mHistoryDatabase;
    private List<SearchItem> mSuggestionsList;
    private SearchView mSearchView;
    private int mVersion = SearchCodes.VERSION_TOOLBAR;
    private int mStyle = SearchCodes.STYLE_TOOLBAR_CLASSIC;
    private int mTheme = SearchCodes.THEME_LIGHT;

    private static final String TAG = SearchResultsFragment.class.getName();
    private Bus mBus;
    private String request;

    private TweetTagAdapter brandAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mVersion = extras.getInt("version");
            mStyle = extras.getInt("style");
            mTheme = extras.getInt("theme");

            if (mTheme == SearchCodes.THEME_LIGHT) {
                setTheme(R.style.AppThemeLight);
                checkedMenuItem = 0;
            }

            if (mTheme == SearchCodes.THEME_DARK) {
                setTheme(R.style.AppThemeDark);
                checkedMenuItem = 1;
            }
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        final FloatingActionButton mFab = (FloatingActionButton) findViewById(R.id.fab_git_hub_source);
        // TODO ADD MENU ITEM CLICK LISTENER


        mHistoryDatabase = new SearchHistoryTable(this);
        mSuggestionsList = new ArrayList<>();

        mSearchView = (SearchView) findViewById(R.id.searchView);
        // important -------------------------------------------------------------------------------
        mSearchView.setVersion(mVersion);
        mSearchView.setStyle(mStyle);
        mSearchView.setTheme(mTheme);
        // -----------------------------------------------------------------------------------------
        mSearchView.setDivider(false);
        mSearchView.setHint("Search");
        mSearchView.setHint(R.string.search);
        mSearchView.setHintSize(getResources().getDimension(R.dimen.search_text_medium));
        mSearchView.setVoice(true);
        mSearchView.setVoiceText("Voice");
        mSearchView.setAnimationDuration(360);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mHistoryDatabase.addItem(new SearchItem(query));
                Toast.makeText(getApplicationContext(), query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mSearchView.setOnSearchViewListener(new SearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                mFab.hide();
            }

            @Override
            public void onSearchViewClosed() {
                mFab.show();
            }
        });

        List<SearchItem> mResultsList = new ArrayList<>();
        SearchAdapter mSearchAdapter = new SearchAdapter(this, mResultsList, mSuggestionsList, mTheme);
        mSearchAdapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView textView = (TextView) view.findViewById(R.id.textView_item_text);
                CharSequence text = textView.getText();
                mHistoryDatabase.addItem(new SearchItem(text));
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
               // brandAdapter = new TweetTagAdapter();
               // brandAdapter.addAll(new TweetTagList());
            }
        });

        mSearchView.setAdapter(mSearchAdapter);

        showSearchView();
    }

    @Override
    public void onStart() {
        super.onStart();
        getBus().register(this);
        if (TextUtils.isEmpty(PrefsController.getAccessToken(getApplicationContext()))) {
            getBus().post(new TwitterGetTokenEvent());
        } else {
            String token = PrefsController.getAccessToken(getApplicationContext());
           // getBus().post(new SearchTweetsEvent(token, request));
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        getBus().unregister(this);
    }

    @Override
    public void onBackPressed() {
        if (mSearchView.isSearchOpen() && mSearchView.isSearchOpen()) {
            mSearchView.clearFocusedItem();
            mSearchView.hide(true);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SearchView.SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (results != null && results.size() > 0) {
                String searchWrd = results.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    mSearchView.setQuery(searchWrd);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showSearchView() {
        mSuggestionsList.clear();
        mSuggestionsList.addAll(mHistoryDatabase.getAllItems());
        mSuggestionsList.add(new SearchItem("Lagos"));
        mSuggestionsList.add(new SearchItem("Kwara"));
        mSuggestionsList.add(new SearchItem("Ogun"));
        mSuggestionsList.add(new SearchItem("Oyo"));
        mSuggestionsList.add(new SearchItem("Plateau"));
        mSuggestionsList.add(new SearchItem("Benue"));
        mSuggestionsList.add(new SearchItem("Osun"));
        mSuggestionsList.add(new SearchItem("Kwara"));
        mSuggestionsList.add(new SearchItem("Rivers"));
        mSuggestionsList.add(new SearchItem("Delta"));
        // mSearchView.openSearchView(true);
    }

    /*@Override
    public void onClick(View view) {

        Intent intent;

        switch (view.getId()) {
            case R.id.button_delete:
                Snackbar.make(view, "Search history deleted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                mHistoryDatabase.clearDatabase();
                break;
            case R.id.button_classic:
                intent = new Intent(this, MenuItemActivity.class);
                intent.putExtra("version", SearchCodes.VERSION_MENU_ITEM);
                intent.putExtra("style", SearchCodes.STYLE_MENU_ITEM_CLASSIC);
                if (mTheme == SearchCodes.THEME_LIGHT) {
                    intent.putExtra("theme", SearchCodes.THEME_LIGHT);
                }
                if (mTheme == SearchCodes.THEME_DARK) {
                    intent.putExtra("theme", SearchCodes.THEME_DARK);
                }
                startActivity(intent);
                break;
            case R.id.button_color:
                intent = new Intent(this, MenuItemActivity.class);
                intent.putExtra("version", SearchCodes.VERSION_MENU_ITEM);
                intent.putExtra("style", SearchCodes.STYLE_MENU_ITEM_COLOR);
                if (mTheme == SearchCodes.THEME_LIGHT) {
                    intent.putExtra("theme", SearchCodes.THEME_LIGHT);
                }
                if (mTheme == SearchCodes.THEME_DARK) {
                    intent.putExtra("theme", SearchCodes.THEME_DARK);
                }
                startActivity(intent);
                break;
            case R.id.fab_git_hub_source:
                String url = "https://github.com/lapism/SearchView";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            case R.id.checkableImageView:
                Snackbar.make(view, "Search history deleted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                mHistoryDatabase.clearDatabase();
                break;
            default:
                break;
        }
    }*/

    @Subscribe
    public void onTwitterGetTokenOk(TwitterGetTokenEventOk event) {
       // getBus().post(new SearchTweetsTagEvent(PrefsController.getAccessToken(getApplicationContext()), request));
    }

    @Subscribe
    public void onTwitterGetTokenFailed(TwitterGetTokenEventFailed event) {
        makeToast(getApplicationContext(), "Failed to get token");
    }
    @Subscribe
    public void onSearchTweetsEventOk(final SearchTweetsTagEventOk event) {
       // brandAdapter.setTweetTagList(event.tweetsList);
        brandAdapter.notifyDataSetChanged();
    }

    @Subscribe
    public void onSearchTweetsEventFailed(SearchTweetsEventFailed event) {
        makeToast(getApplicationContext(), "Search of tweets failed");
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
