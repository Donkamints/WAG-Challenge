package com.example.tj.wagchallenge;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<WalkerInfo> m_Dataset;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected View m_parentView;
        protected LinearLayout m_container;
        protected TextView m_bronzeBadgesText;
        protected TextView m_silverBadgesText;
        protected TextView m_goldBadgesText;
        protected TextView m_display_name;
        public TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.text_view);
            this.m_parentView = v;
            this.m_container = (LinearLayout)v.findViewById(R.id.layout_container);
            this.m_bronzeBadgesText = (TextView)v.findViewById(R.id.bronze_badges);
            this.m_silverBadgesText = (TextView)v.findViewById(R.id.silver_badges);
            this.m_goldBadgesText = (TextView)v.findViewById(R.id.gold_badges);
            this.m_display_name = (TextView)v.findViewById(R.id.display_name);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<WalkerInfo> myDataset) {
        m_Dataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
       View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.walker_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
       final WalkerInfo walkerInfo = m_Dataset.get(position);
        holder.m_bronzeBadgesText.setText(walkerInfo.m_bronzeBadges);
        holder.m_silverBadgesText.setText(walkerInfo.m_silverBadges);
        holder.m_goldBadgesText.setText(walkerInfo.m_goldBadges);
        holder.m_display_name.setText(walkerInfo.m_displayName);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return m_Dataset.size();
    }
}
