package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class AddNeighbourEvent {

    public Neighbour randomNeighbour;

    public AddNeighbourEvent(Neighbour neighbour) {this.randomNeighbour=neighbour;}
}
