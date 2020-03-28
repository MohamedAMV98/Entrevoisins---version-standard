package com.openclassrooms.entrevoisins.service;

import android.util.Log;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;

import static android.support.constraint.Constraints.TAG;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private Neighbour theNeighbour;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }


    // AJOUT POUR RÉVISION DU CODE APRÈS SOUTENANCE


    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        List<Neighbour> favoriteList = new ArrayList<>();
        for(Neighbour neighbour : neighbours){
            if (neighbour.getFavOrNot())
                favoriteList.add(neighbour);
        }
        return favoriteList;
    }

    @Override
    public void setNeighbourFav(long neighbourID){
        for(Neighbour neighbour : neighbours){
            if(neighbour.getId() == neighbourID)
                neighbour.setFavOrNot(true);
        }
    }

    @Override
    public void setNeighbourUnfav(long neighbourID){
        for(Neighbour neighbour : neighbours){
            if(neighbour.getId() == neighbourID)
                neighbour.setFavOrNot(false);
        }
    }

    @Override
    public Neighbour getTheNeighbour(long ID) {
        for (Neighbour neighbour : neighbours){
            if (neighbour.getId() == ID)
                theNeighbour = new Neighbour(ID, neighbour.getName(), neighbour.getAvatarUrl(), neighbour.getAddress(), neighbour.getPhoneNumber(),
                        neighbour.getAboutMe(), neighbour.getFavOrNot());
        }
        return theNeighbour;
    }
}
