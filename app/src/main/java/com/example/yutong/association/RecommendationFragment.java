package com.example.yutong.association;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yalantis.phoenix.PullToRefreshView;

/**
 * Created by yutong on 2016/2/13.
 */
public class RecommendationFragment extends Fragment {
    private PullToRefreshView mPullToRefreshView;

    public RecommendationFragment(){

    }
    public static RecommendationFragment newInstance() {
        RecommendationFragment fragment = new RecommendationFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recommendation, container, false);

        mPullToRefreshView = (PullToRefreshView) rootView.findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        return rootView;
    }

}

