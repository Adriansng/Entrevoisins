package com.openclassrooms.entrevoisins.service;


import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favoriteNeighbour = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }



    @Override
    public List<Neighbour> getFavoriteNeighbours(){
       favoriteNeighbour.clear();
       for (Neighbour n : neighbours) {
           if(n.getFavorite()){
               favoriteNeighbour.add(n);
           }
       }
        return favoriteNeighbour;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
        favoriteNeighbour.remove(neighbour);
    }

    @Override
    public Neighbour getNeighbours(int id) {
        return neighbours.get(id-1);
    }


}
