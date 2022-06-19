package com.cuoiky.andoid.dictionaryapp.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.cuoiky.andoid.dictionaryapp.data.model.wordsapi.Word;

@Database(entities = {Word.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract WordsDao wordsDao();
    private static AppDatabase instance;
    public static AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "dictionaryApp").build();
        }
        return instance;
    }
}
