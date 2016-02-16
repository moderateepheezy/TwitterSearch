package org.sergez.twsearch.util;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.sergez.twsearch.R;
import org.sergez.twsearch.api.TrendList;
import org.sergez.twsearch.api.TweetList;
import org.sergez.twsearch.api.TweetTag;
import org.sergez.twsearch.api.TweetTagList;

import com.ftinc.kit.adapter.BetterRecyclerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by simpumind on 2/15/16.
 */
/*public class TweetTagAdapter extends BetterRecyclerAdapter<TweetTagList, TweetTagAdapter.OSViewHolder>{

    @Override
    public OSViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new OSViewHolder(view);
    }

    public void setTweetList(TweetTagList tweetList) {
        this.items.add(tweetList);
    }

    @Override
    public void add(TweetTagList object) {
        super.add(object);
    }

    @Override
    public void onBindViewHolder(OSViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        TweetTagList os = getItem(i);
        viewHolder.tweetName.setText(os.tweetTag.get(i).nameTag);
        viewHolder.tweetUrl.setText(os.tweetTag.get(i).tweetUrl);
        viewHolder.tweetVolume.setText(os.tweetTag.get(i).tweetVolume);
    }

    public static class OSViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.tweet_name)         public TextView tweetName;
        @Bind(R.id.tweet_url)   public TextView tweetUrl;
        @Bind(R.id.tweet_volume)   public TextView tweetVolume;

        public OSViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}*/

public class TweetTagAdapter extends BaseAdapter {

    private Context mContext;
    private List<TrendList> tweetList;

    public TweetTagAdapter(Context mContext, ArrayList tweetList) {
        this.mContext = mContext;
        this.tweetList = tweetList;
    }

    public void setTweetTagList(List<TrendList> tweetList) {
        this.tweetList.addAll(tweetList);
    }

    @Override
    public int getCount() {
        if (tweetList != null) {
            return tweetList.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return null; // we don't need it now
    }

    @Override
    public long getItemId(int position) {
        return 0; // we don't need it now
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.tweetname = (TextView) row.findViewById(R.id.tweet_name);
            holder.tweetUrl = (TextView) row.findViewById(R.id.tweet_url);
            holder.tweetVolume = (TextView) row.findViewById(R.id.tweet_volume);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        holder.tweetname.setText(tweetList.get(position).trends.get(position).getName());
        holder.tweetUrl.setText(tweetList.get(position).trends.get(position).getUrl());
        holder.tweetVolume.setText(String.valueOf(tweetList.get(position).trends.get(position).getTweetVolume()));
        return row;
    }

    static class ViewHolder {
        TextView tweetname;
        TextView tweetUrl;
        TextView tweetVolume;
    }

}
