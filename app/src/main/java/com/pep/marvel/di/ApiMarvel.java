package com.pep.marvel.di;

import com.pep.marvel.model.response.DataWrapper;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiMarvel {

    String TIMESTAMP = "ts";
    String API_KEY = "apikey";
    String HASH = "hash";


    @GET("characters")
    Call<DataWrapper> getCharacter(
            @Query(API_KEY) String publicKey,
            @Query(HASH) String hash,
            @Query(TIMESTAMP) long timestamp);


    @GET("characters/{characterId}")
    Call<DataWrapper> getCharacterID(
            @Path("characterId") String characterId,
            @Query(API_KEY) String publicKey,
            @Query(HASH) String hash,
            @Query(TIMESTAMP) long timestamp);



    @GET("comics")
    Call<DataWrapper> getComics(
            @Query(API_KEY) String publicKey,
            @Query(HASH) String hash,
            @Query(TIMESTAMP) long timestamp);


    @GET("comics/{comicsId}")
    Call<DataWrapper> getComicsID(
            @Path("comicsId") String comicsId,
            @Query(API_KEY) String publicKey,
            @Query(HASH) String hash,
            @Query(TIMESTAMP) long timestamp);



    @GET("creators")
    Call<DataWrapper> getCreators(
            @Query(API_KEY) String publicKey,
            @Query(HASH) String hash,
            @Query(TIMESTAMP) long timestamp);


    @GET("creators/{creatorsId}")
    Call<DataWrapper> getCreatorsID(
            @Path("creatorsId") String creatorsId,
            @Query(API_KEY) String publicKey,
            @Query(HASH) String hash,
            @Query(TIMESTAMP) long timestamp);




    @GET("events")
    Call<DataWrapper> getEvents(
            @Query(API_KEY) String publicKey,
            @Query(HASH) String hash,
            @Query(TIMESTAMP) long timestamp);


    @GET("events/{eventsId}")
    Call<DataWrapper> getEventsID(
            @Path("eventsId") String eventsId,
            @Query(API_KEY) String publicKey,
            @Query(HASH) String hash,
            @Query(TIMESTAMP) long timestamp);




    @GET("series")
    Call<DataWrapper> getSeries(
            @Query(API_KEY) String publicKey,
            @Query(HASH) String hash,
            @Query(TIMESTAMP) long timestamp);


    @GET("series/{seriesId}")
    Call<DataWrapper> getSeriesID(
            @Path("seriesId") String seriesId,
            @Query(API_KEY) String publicKey,
            @Query(HASH) String hash,
            @Query(TIMESTAMP) long timestamp);




    @GET("stories")
    Call<DataWrapper> getStories(
            @Query(API_KEY) String publicKey,
            @Query(HASH) String hash,
            @Query(TIMESTAMP) long timestamp);


    @GET("stories/{storiesId}")
    Call<DataWrapper> getStoriesID(
            @Path("storiesId") String storiesId,
            @Query(API_KEY) String publicKey,
            @Query(HASH) String hash,
            @Query(TIMESTAMP) long timestamp);


}
