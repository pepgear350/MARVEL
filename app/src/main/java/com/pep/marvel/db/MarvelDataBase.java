package com.pep.marvel.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.pep.marvel.db.dao.MarvelDAO;
import com.pep.marvel.db.entity.MarvelEntity;

@Database(entities = MarvelEntity.class, version = 1 , exportSchema = false)

public abstract class MarvelDataBase extends RoomDatabase {

    public abstract MarvelDAO marvelDAO();

}
