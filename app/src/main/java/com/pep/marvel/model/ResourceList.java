package com.pep.marvel.model;

import com.google.gson.annotations.SerializedName;

public class ResourceList {

    @SerializedName("available")
    private String available;

    @SerializedName("returned")
    private int returned;

    @SerializedName("collectionURI")
    private String collectionURI;

    @SerializedName("items")
    private Items[] items;


    public ResourceList(String available, int returned, String collectionURI, Items[] items) {
        this.available = available;
        this.returned = returned;
        this.collectionURI = collectionURI;
        this.items = items;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public Items[] getItems() {
        return items;
    }

    public void setItems(Items[] items) {
        this.items = items;
    }
}
