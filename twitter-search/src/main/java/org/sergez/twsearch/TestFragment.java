/*
package org.sergez.twsearch;

import android.app.ListFragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import org.sergez.twsearch.api.ApiConstants;
import org.sergez.twsearch.api.Trends;
import org.sergez.twsearch.api.TwitterApiService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

*/
/**
 * Created by simpumind on 2/16/16.
 *//*

public class TestFragment extends ListFragment{

    public static final String ROOT_URL = ApiConstants.TWITTER_TREND_SEARCH_CODE;
    private List<Trend> trend;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_tweets, container, false);

        return rootView;
    }

    private void getBooks(){
        //While the app fetched data we are displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(getActivity(),"Fetching Data","Please wait...",false,false);

        //Creating a rest adapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        //Creating an object of our api interface
        TwitterApiService api = adapter.create(TwitterApiService.class);

        //Defining the method
        api.getBooks(new Callback<List<Trend>>() {
            @Override
            public void success(List<Trend> list, Response response) {
                //Dismissing the loading progressbar
                loading.dismiss();

                //Storing the data in our list
               //  = list;

                //Calling a method to show the list
                showList();
            }

            @Override
            public void failure(RetrofitError error) {
                //you can handle the errors here
            }
        });
    }

    //Our method to show list
    private void showList(){
        //String array to store all the book names
        String[] items = new String[trend.size()];

        //Traversing through the whole list to get all the names
        for(int i=0; i<trend.size(); i++){
            //Storing names to string array
            items[i] = trend.get(i).getName();
        }

        //Creating an array adapter for list view
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.simple_list,items);

        //Setting adapter to listview
        setListAdapter(adapter);
    }

}
*/
