package com.pep.marvel.model.response;

import com.google.gson.annotations.SerializedName;

public class DataContainer {

    @SerializedName("count")
    private int count;

    @SerializedName("results")
    private Results[] results;


    public DataContainer(int count, Results[] results) {
        this.count = count;
        this.results = results;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Results[] getResults() {
        return results;
    }

    public void setResults(Results[] results) {
        this.results = results;
    }
}
