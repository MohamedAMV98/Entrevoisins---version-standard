package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllinOneFragment extends Fragment {

    private NeighbourApiService mApiService;
    private RecyclerView mRecyclerView;
    public List<Neighbour> mNeighbours;
    public static final String BOOL = "bool";
    boolean isBool;

    public static AllinOneFragment newInstance(boolean whichTab) {
        AllinOneFragment fragment = new AllinOneFragment();
        Bundle bd =  new Bundle();
        bd.putBoolean(BOOL, whichTab);
        fragment.setArguments(bd);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
        if(getArguments()!= null) {
            isBool = getArguments().getBoolean(BOOL);
            if (getArguments().getBoolean(BOOL))
                mNeighbours = mApiService.getFavoriteNeighbours();
            else
                mNeighbours = mApiService.getNeighbours();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    /**
     * Init the List of neighbours depending on boolean variable getWhichTab
     */
    private void initList() {
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours, isBool));
    }

    @Override
    public void onResume() {
        initList();
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        if(isBool) {
            mApiService.deleteNeighbour(event.neighbour);
            initList();
        }
        else {
            mApiService.deleteFavNeighbour(event.neighbour);
            initList();
        }
    }
}
