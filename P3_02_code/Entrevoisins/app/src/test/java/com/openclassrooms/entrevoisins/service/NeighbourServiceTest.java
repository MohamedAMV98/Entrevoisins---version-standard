package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsArrayContaining;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    // TEST ADDED

    @Test
    public void addNeighbourWithSuccess(){
        Neighbour neighbourToAdd = new Neighbour(13, "Jean", "https://i.pravatar.cc/150?u=a042581f4e29026703b",
                "24 Rue Jules Guérin", "07 61 77 66 33", "this is me");
        service.createNeighbour(neighbourToAdd);
        assertTrue(service.getNeighbours().contains(neighbourToAdd));
    }

    @Test
    public void addNeighbourToFavoriteWithSuccess(){
        Neighbour neighbourToAddToFavorite = service.getNeighbours().get(0);
        service.createFavoriteNeighbour(neighbourToAddToFavorite);
        assertTrue(service.getFavoriteNeighbours().contains(neighbourToAddToFavorite));
    }

    @Test
    public void removeNeighbourFromFavoriteList(){
        Neighbour neighbourToAddToFavorite = service.getNeighbours().get(0);
        // CHECK IF NEIGHBOUR IS ADDED THEN WE CAN REMOVE IT
        service.createFavoriteNeighbour(neighbourToAddToFavorite);
        assertTrue(service.getFavoriteNeighbours().contains(neighbourToAddToFavorite));
        // CHECK IF NEIGHBOUR IS REMOVED
        service.deleteFavNeighbour(neighbourToAddToFavorite);
        assertFalse(service.getFavoriteNeighbours().contains(neighbourToAddToFavorite));
    }
}
