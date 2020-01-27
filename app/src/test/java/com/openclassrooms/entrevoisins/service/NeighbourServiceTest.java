package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
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
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(Objects.requireNonNull(expectedNeighbours.toArray())));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void addNeighbourFavorite() {
        Neighbour neighbour= service.getNeighbours().get(0);
        assertEquals(3,service.getFavoriteNeighbours().size());
        assertFalse(neighbour.getFavorite());
        neighbour.setFavorite(true);
        assertEquals(4,service.getFavoriteNeighbours().size());
        assertTrue(neighbour.getFavorite());
    }

    @Test
    public void detailAvatarNeighbour() {
        Neighbour neighbour= service.getNeighbours().get(0);
        neighbour.setAvatarUrl("Url");
        neighbour.setName("Name");
        assertEquals("Url",neighbour.getAvatarUrl());
        assertEquals("Name",neighbour.getName());
    }

    @Test
    public void addRandomNeighbour() {
        service.getNeighbours().clear();
        service.addRandomNeighbour(Neighbour.random());
        Neighbour newNeighbour = service.getNeighbours().get(0);
        assertEquals(1,service.getNeighbours().size());
        assertTrue(DummyNeighbourGenerator.DUMMY_NEIGHBOURS.stream().map(Neighbour::getAvatarUrl).collect(Collectors.toList()).contains(newNeighbour.getAvatarUrl()));
        assertTrue(DummyNeighbourGenerator.DUMMY_NEIGHBOURS.stream().map(Neighbour::getName).collect(Collectors.toList()).contains(newNeighbour.getName()));
        assertTrue(DummyNeighbourGenerator.DUMMY_NEIGHBOURS.stream().map(Neighbour::getId).collect(Collectors.toList()).contains(newNeighbour.getId()));
        assertTrue(DummyNeighbourGenerator.DUMMY_NEIGHBOURS.stream().map(Neighbour::getFavorite).collect(Collectors.toList()).contains(newNeighbour.getFavorite()));
    }
}
