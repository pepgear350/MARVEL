package com.pep.marvel.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pep.marvel.db.entity.MarvelEntity;

import java.util.List;

import retrofit2.http.DELETE;

@Dao
public interface MarvelDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveEntitys(List<MarvelEntity> charactersEntities);


    @Query("SELECT * FROM marvel ORDER BY name ASC")
    LiveData<List<MarvelEntity>> loadAllEntitys();


    @Query("SELECT * FROM marvel ORDER BY name ASC")
    List<MarvelEntity> loadAllEntitysSyn();


    @Query("SELECT * FROM marvel WHERE id = :characterID")
    LiveData<List<MarvelEntity>> loadIDEntitys(int characterID);


    @Query("SELECT * FROM marvel WHERE name LIKE  '%' ||:name|| '%'")
   List<MarvelEntity>loadLikeEntitys(String name);

    @Query("DELETE FROM marvel")
    void deleteAll();

}
