package io.harshproject.moviecatalogservice.models;

public class CatalogItem {

    private String name;
    private String disc;
    private float rating;

    public CatalogItem() {
    }

    public CatalogItem(String name, String disc, float rating) {
        this.name = name;
        this.disc = disc;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
