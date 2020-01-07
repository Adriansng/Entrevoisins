package com.openclassrooms.entrevoisins.service;

import android.annotation.SuppressLint;
import android.os.Build;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.Collection;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favoriteNeighbour = DummyNeighbourGenerator.generateNeighbours();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }



    @Override
    public List<Neighbour> getFavoriteNeighbours(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            favoriteNeighbour.removeIf(neighbour -> !neighbour.getFavorite());
        }
        return favoriteNeighbour;}
    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
        favoriteNeighbour.remove(neighbour);
    }


}
