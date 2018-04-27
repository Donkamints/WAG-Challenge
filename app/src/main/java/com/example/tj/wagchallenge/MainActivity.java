package com.example.tj.wagchallenge;

import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<WalkerInfo> m_walkerInfoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Make sure to create the list or you will crash when you try to add a walker to it
        m_walkerInfoList = new ArrayList<WalkerInfo>();

        //set up the RecyclerView
        mRecyclerView = findViewById(R.id.recycler_view);



        new FetchWalkerInfo().execute();

    }

    private class FetchWalkerInfo extends AsyncTask<Void, Void, Void> {

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

            //The RecyclerView needs to be set up here otherwise it wont have data to populate
            mLayoutManager = new LinearLayoutManager(MainActivity.this);
            mRecyclerView.setLayoutManager(mLayoutManager);

            //set up the adapter and pass it to the RecyclerView
            mAdapter = new WalkerAdapter(m_walkerInfoList);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}





