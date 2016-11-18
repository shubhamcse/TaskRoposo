package com.shubham.taskroposo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Shubham Gupta on 18-11-2016.
 */

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

   public class ListViewHolder extends RecyclerView.ViewHolder{

       public ListViewHolder(View itemView) {
           super(itemView);
       }
   }
}
