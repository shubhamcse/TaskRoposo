package com.shubham.taskroposo.adapers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shubham.taskroposo.R;

/**
 * Created by Shubham Gupta on 19-11-2016.
 */

public class ListDetailAdapter extends ListAdapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     super.onCreateViewHolder(parent, viewType);
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_list_item, parent, false);
        DetailViewHolder listViewHolder = new DetailViewHolder(view);
        return  listViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        DetailViewHolder detailViewHolder = (DetailViewHolder)holder;
        detailViewHolder.mTextDescription.setText(stories.get(position).getDescription());
        detailViewHolder.mTextURL.setText(stories.get(position).getUrl());

    }
    class DetailViewHolder extends ListViewHolder{
        public DetailViewHolder(View view){
            super(view);
            mTextURL.setVisibility(View.VISIBLE);
            mTextDescription.setVisibility(View.VISIBLE);
        }
    }
}
