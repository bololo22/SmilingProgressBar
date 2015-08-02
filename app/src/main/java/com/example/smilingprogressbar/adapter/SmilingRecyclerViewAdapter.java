package com.example.smilingprogressbar.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smilingprogressbar.R;
import com.example.smilingprogressbar.SmilingProgressBarView;

import java.util.List;

/**
 * Created by Android1 on 7/7/2015.
 */
public class SmilingRecyclerViewAdapter extends RecyclerView.Adapter<SmilingRecyclerViewAdapter.ViewHolder>{

    private List<Integer> mList;

    public SmilingRecyclerViewAdapter(List<Integer> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_smiling_progress, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        int value = mList.get(i);
        final int position = i;

        viewHolder.progressBar.setProgress(value);

        viewHolder.progressBar.onProgressChange(new OnProgressBarChange() {
            @Override
            public void onChange(int value){
                mList.set(position, value);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<Integer> getList() {
        return mList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public SmilingProgressBarView progressBar;

        public ViewHolder(View itemView) {
            super(itemView);

            progressBar = (SmilingProgressBarView) itemView.findViewById(R.id.mySmilingProgressBar);
        }
    }
}
