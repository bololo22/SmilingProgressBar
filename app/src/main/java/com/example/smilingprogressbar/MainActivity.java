package com.example.smilingprogressbar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.smilingprogressbar.adapter.SmilingRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getName();
    private static final String LIST_TAG = "progressList";
    private RecyclerView mRecyclerView;
    private SmilingRecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerViewAdapter = new SmilingRecyclerViewAdapter(new ArrayList<Integer>());

        setupRecyclerView();

        if (savedInstanceState == null) {
            for(int i = 0; i < 10; i++){
                mRecyclerViewAdapter.getList().add(i);
            }
        } 

        mRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        List<Integer> list = mRecyclerViewAdapter.getList();
        outState.putIntegerArrayList(LIST_TAG, (ArrayList<Integer>) list);

        for (int i = 0; i < list.size(); i++) {
            Log.e(TAG, "Item " + i + ": " + list.get(i));
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mRecyclerViewAdapter.getList().clear();
        List<Integer> array = savedInstanceState.getIntegerArrayList(LIST_TAG);
        mRecyclerViewAdapter.getList().addAll(array);
    }

    private void setupRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.myrRecyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }


}
