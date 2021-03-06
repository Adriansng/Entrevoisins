package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    //Get favorite Neighbours

    List<Neighbour> getFavoriteNeighbours();


    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * get a single neighbour by id
     *@param id
     */
    Neighbour getNeighbours(int id);


    //Generate random Neighbour

    void addRandomNeighbour(Neighbour randomNeighbour);
}
