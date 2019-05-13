package com.pep.marvel.model.response;


import com.google.gson.annotations.SerializedName;
import com.pep.marvel.model.Image;
import com.pep.marvel.model.ResourceList;

public class Results {


    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("title")
    private String title;

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("description")
    private String description;

    @SerializedName("thumbnail")
    private Image thumbnail;

    @SerializedName("comics")
    private ResourceList comics;

    @SerializedName("stories")
    private ResourceList stories;

    @SerializedName("events")
    private ResourceList events;

    @SerializedName("series")
    private ResourceList series;

    @SerializedName("characters")
    private ResourceList characters;

    @SerializedName("creators")
    private ResourceList creators;





    public Results(int id, String name, String fullName, String description, Image thumbnail, ResourceList comics, ResourceList stories, ResourceList events, ResourceList series, String title, ResourceList characters, ResourceList creators) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.description = description;
        this.thumbnail = thumbnail;
        this.comics = comics;
        this.stories = stories;
        this.events = events;
        this.series = series;
        this.title = title;
        this.characters = characters;
        this.creators = creators;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }

    public ResourceList getComics() {
        return comics;
    }

    public void setComics(ResourceList comics) {
        this.comics = comics;
    }

    public ResourceList getStories() {
        return stories;
    }

    public void setStories(ResourceList stories) {
        this.stories = stories;
    }

    public ResourceList getEvents() {
        return events;
    }

    public void setEvents(ResourceList events) {
        this.events = events;
    }

    public ResourceList getSeries() {
        return series;
    }

    public void setSeries(ResourceList series) {
        this.series = series;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ResourceList getCharacters() {
        return characters;
    }

    public void setCharacters(ResourceList characters) {
        this.characters = characters;
    }

    public ResourceList getCreators() {
        return creators;
    }

    public void setCreators(ResourceList creators) {
        this.creators = creators;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
