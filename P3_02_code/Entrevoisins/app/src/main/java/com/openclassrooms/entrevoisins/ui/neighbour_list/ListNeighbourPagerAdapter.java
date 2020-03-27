package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */

    NeighbourApiService mApiService = DI.getNeighbourApiService();

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return AllinOneFragment.newInstance(mApiService.getNeighbours());
            case 1:
                return AllinOneFragment.newInstance(mApiService.getFavoriteNeighbours());
        }
        return null;
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return "MY NEIGHBOURS";
            case 1:
                return "FAVORITES";
        }
        return null;
    }



}