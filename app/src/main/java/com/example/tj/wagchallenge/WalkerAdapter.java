package com.example.tj.wagchallenge;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class WalkerAdapter extends RecyclerView.Adapter<WalkerAdapter.ViewHolder> {

    private final String TAG = WalkerAdapter.class.getSimpleName();
    private ArrayList<WalkerInfo> m_Dataset;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected View m_parentView;
        protected RelativeLayout m_container;
        protected TextView m_bronzeBadgesText;
        protected TextView m_silverBadgesText;
        protected TextView m_goldBadgesText;
        protected TextView m_displayName;
        protected ProgressBar m_progressBar;
        protected ImageView m_gravatarImage;


        public ViewHolder(View v) {
            super(v);
            this.m_parentView = v;
            this.m_container = v.findViewById(R.id.layout_container);
            this.m_bronzeBadgesText = v.findViewById(R.id.bronze_badges);
            this.m_silverBadgesText = v.findViewById(R.id.silver_badges);
            this.m_goldBadgesText = v.findViewById(R.id.gold_badges);
            this.m_displayName = v.findViewById(R.id.display_name);
            this.m_progressBar =  v.findViewById(R.id.progress_bar);
            this.m_gravatarImage = v.findViewById(R.id.gravatar_image);

      }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public WalkerAdapter(ArrayList<WalkerInfo> myDataset) {
        m_Dataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                   int viewType) {
        // create a new view
       View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.walker_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
       final WalkerInfo walkerInfo = m_Dataset.get(position);
        holder.m_bronzeBadgesText.setText(walkerInfo.getM_bronzeBadges());
        holder.m_silverBadgesText.setText(walkerInfo.getM_silverBadges());
        holder.m_goldBadgesText.setText(walkerInfo.getM_goldBadges());
        holder.m_displayName.setText(walkerInfo.getM_displayName());

        //Invoke the Picasso Library to download the Gravatar and set the ImageView
        Picasso.get().load(walkerInfo.getM_profileImageURL()).into(holder.m_gravatarImage);
        holder.m_gravatarImage.setImageDrawable(holder.m_gravatarImage.getDrawable());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return m_Dataset.size();
    }
}
