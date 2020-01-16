package com.openclassrooms.entrevoisins.service;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.DetailNeighbourActivity;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static java.lang.System.load;
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
    private DetailNeighbourActivity detailActivity;

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
    public void DetailAvatarNeighbour() {
        Neighbour neighbour= service.getNeighbours().get(0);
        neighbour.setAvatarUrl("Url");
        neighbour.setName("Name");
        assertEquals("Url",neighbour.getAvatarUrl());
        assertEquals("Name",neighbour.getName());
    }
}
