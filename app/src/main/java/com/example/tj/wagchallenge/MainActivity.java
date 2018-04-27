package com.example.tj.wagchallenge;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * This Serves as the MainActivity within the application
 */

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    private static RecyclerView mRecyclerView;
    private static RecyclerView.LayoutManager mLayoutManager;
    private static RecyclerView.Adapter mAdapter;
    private static ArrayList<WalkerInfo> m_walkerInfoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Make sure to create the list or you will crash when you try to add a walker to it
        m_walkerInfoList = new ArrayList<WalkerInfo>();

        //set up the RecyclerView
        mRecyclerView = findViewById(R.id.recycler_view);

        //The RecyclerView needs to be set up here otherwise it wont have data to populate
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);



        new FetchWalkerInfo().execute();

    }

    /**
     * A class that goes out and fetches the profile JSON data  from the server
     * then builds a WalkerInfo to store it and pushes it to our dataset List.
     *
     * This class extends AsyncTask to make sure the download happens in the background
     * so it doesn't interfere with the UI thread and the user experience.
     */
    private static class FetchWalkerInfo extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            UrlHandler urlHandler = new UrlHandler();

            // Making a request to url and getting response
            String url = "https://api.stackexchange.com/2.2/users?site=stackoverflow";
            String jsonStr = urlHandler.makeUrlConnection(url);

            Log.wtf(TAG, "Response from stackExchange: " + jsonStr);
            if (jsonStr != null) {

                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);

                    //Get JSON Array of Objects
                    JSONArray jsonArrayItems = jsonObject.getJSONArray("items");

                    //Loop through and grab the data
                    for (int i = 0; i < jsonArrayItems.length(); i++) {
                        JSONObject items = jsonArrayItems.getJSONObject(i);

                        //get the number of badges: bronze silver and gold
                        JSONObject badgeCounts = items.getJSONObject("badge_counts");
                        String bronze_badges = badgeCounts.getString("bronze");
                        String silver_badges = badgeCounts.getString("silver");
                        String gold_badges = badgeCounts.getString("gold");

                        //get the display name
                        String displayName = items.getString("display_name");

                        //get the gravatar URL
                        String profileImageURL = items.getString("profile_image");

                        //create a walkerInfo
                        WalkerInfo tempWalker = new WalkerInfo(bronze_badges, silver_badges,
                                gold_badges, profileImageURL, displayName);

                        //push the newly created walker onto the walkerInfoList
                       m_walkerInfoList.add(tempWalker);

                    }
                } catch (final JSONException e) {
                   Log.wtf(TAG, "Json parsing error: " + e.getMessage());

                }
            } else {
                Log.wtf(TAG, "Couldn't get json from server.");

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            //sort the list before passing it off to the adapter
            //sort by amount of gold badges
            Collections.sort(m_walkerInfoList,new GoldWalkerSort());

            //set up the adapter and pass it to the RecyclerView
            mAdapter = new WalkerAdapter(m_walkerInfoList);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    /**
     * A custom sort used to sort our Walkers by the amount of gold badges
     */
    public static class GoldWalkerSort implements Comparator<WalkerInfo> {
        @Override
        public int compare(WalkerInfo o1, WalkerInfo o2) {
            return Integer.valueOf(o2.getM_goldBadges())
                    .compareTo(Integer.valueOf(o1.getM_goldBadges()));
        }
    }
}





