package com.openclassrooms.entrevoisins.service;
import com.openclassrooms.entrevoisins.model.Neighbour;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class DummyNeighbourGenerator {

        static List<Neighbour> DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(0, "Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d",false),
            new Neighbour(1, "Jack", "http://i.pravatar.cc/150?u=a042581f4e29026704e",false),
            new Neighbour(2, "Chloé", "http://i.pravatar.cc/150?u=a042581f4e29026704f",false),
            new Neighbour(3, "Vincent", "http://i.pravatar.cc/150?u=a042581f4e29026704a",false),
            new Neighbour(4, "Elodie", "http://i.pravatar.cc/150?u=a042581f4e29026704b",false),
            new Neighbour(5, "Sylvain", "http://i.pravatar.cc/150?u=a042581f4e29026704c",false),
            new Neighbour(6, "Laetitia", "http://i.pravatar.cc/150?u=a042581f4e29026703d",true),
            new Neighbour(7, "Dan", "http://i.pravatar.cc/150?u=a042581f4e29026703b",true),
            new Neighbour(8, "Joseph", "http://i.pravatar.cc/150?u=a042581f4e29026704d",true),
            new Neighbour(9, "Emma", "http://i.pravatar.cc/150?u=a042581f4e29026706d",false),
            new Neighbour(10, "Patrick", "http://i.pravatar.cc/150?u=a042581f4e29026702d",false),
            new Neighbour(11, "Ludovic", "http://i.pravatar.cc/150?u=a042581f3e39026702d",false)
    );

    static List<Neighbour> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);
    }
}
