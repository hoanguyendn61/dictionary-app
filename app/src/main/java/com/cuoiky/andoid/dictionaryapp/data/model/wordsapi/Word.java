package com.cuoiky.andoid.dictionaryapp.data.model.wordsapi;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
@Entity(tableName="FavouriteWord", indices = {@Index(value = {"word"}, unique = true)})
public class Word implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="word")
    @SerializedName("word")
    private String word;

    @ColumnInfo(name="results")
    @SerializedName("results")
    private List<Result> results;

    @ColumnInfo(name="pronunciation")
    @SerializedName("pronunciation")
    private Pronunciation pronunciation;

    public Word(String word, List<Result> results, Pronunciation pronunciation) {
        this.word = word;
        this.results = results;
        this.pronunciation = pronunciation;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Pronunciation getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(Pronunciation pronunciation) {
        this.pronunciation = pronunciation;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Word{" +
                "\nword='" + word + '\'' +
                ",\n results=" + results +
                ",\n pronunciation=" + pronunciation +
                '}';
    }
}
