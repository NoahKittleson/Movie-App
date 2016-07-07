package com.example.guest.movieapp;

/**
 * Created by Guest on 7/7/16.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;

import com.example.guest.movieapp.R;
import com.example.guest.movieapp.Preview;
import com.example.guest.movieapp.PreviewDetailActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PreviewListAdapter extends RecyclerView.Adapter<PreviewListAdapter.PreviewViewHolder> {
    private ArrayList<Preview> mPreviews;
    private Context mContext;

    public PreviewListAdapter(Context context, ArrayList<Preview> previews) {
        mContext = context;
        mPreviews = previews;
    }

    @Override
    public PreviewListAdapter.PreviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.preview_list_item, parent, false);
        PreviewViewHolder viewHolder = new PreviewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PreviewListAdapter.PreviewViewHolder holder, int position) {
        holder.bindPreview(mPreviews.get(position));
    }

    @Override
    public int getItemCount() {
        return mPreviews.size();
    }

    public class PreviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.previewTitleTextView) TextView mTitleTextView;
        @Bind(R.id.previewDateTextView) TextView mDateTextView;
        @Bind(R.id.previewRatingTextView) TextView mRatingTextView;
        private Context mContext;

        public PreviewViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, PreviewDetailActivity.class);
            intent.putExtra("position", itemPosition + "");
            intent.putExtra("previews", Parcels.wrap(mPreviews));
            mContext.startActivity(intent);
        }

        public void bindPreview(Preview preview) {
            Log.d("PreviewListAdapter", preview.getTitle() + "");
            mTitleTextView.setText(preview.getTitle());
            mDateTextView.setText(preview.getDate());
            mRatingTextView.setText(preview.getRating() + "/10");
        }
    }


}
