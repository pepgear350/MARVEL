package com.pep.marvel.model;

import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("path")
    private String path;

    @SerializedName("extension")
    private String extension;


    public Image(String path, String extesion) {
        this.path = path;
        this.extension = extesion;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtesion() {
        return extension;
    }

    public void setExtesion(String extesion) {
        this.extension = extesion;
    }
}
