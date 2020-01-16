package com.openclassrooms.entrevoisins.model;

import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;

import java.util.Objects;
import java.util.Random;

/**
 * Model object representing a Neighbour
 */
public class Neighbour {

    /** Identifier */
    private Integer id;

    /** Full name */
    private String name;

    /** Avatar */
    private String avatarUrl;

    /** Favorite */
    private boolean favorite;

    /**
     * Constructor
     * @param id
     * @param name
     * @param avatarUrl
     * @param favorite
     */
    public Neighbour(Integer id, String name, String avatarUrl, boolean favorite) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.favorite = favorite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public boolean getFavorite() { return favorite;}

    public void setFavorite(boolean favorite) {this.favorite = favorite;}

    //Generate random neighbour

    public static Neighbour random() {
        return DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(new Random().nextInt(DummyNeighbourGenerator.DUMMY_NEIGHBOURS.size()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbour neighbour = (Neighbour) o;
        return Objects.equals(id, neighbour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
