package com.cuoiky.andoid.dictionaryapp.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cuoiky.andoid.dictionaryapp.data.model.wordsapi.Word;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface WordsDao {
    @Query("SELECT * FROM FavouriteWord")
    LiveData<List<Word>> getAllFavouriteWords();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertWord(Word w);

    @Delete
    public void removeWord(Word w);
  
    @Delete
    public void removeListofWords(List<Word> w);
  
    @Query("SELECT * FROM FavouriteWord WHERE word LIKE :w LIMIT 1")
    public Single<Word> findWord(String w);
}
