package com.pep.marvel.db.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "marvel")
public class MarvelEntity {

    @PrimaryKey
    @NonNull
    private int id;

    private String name;

    private String thumbnail;

    private String attributionText;


    public MarvelEntity(int id, String name, String thumbnail, String attributionText) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
        this.attributionText = attributionText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }
}
