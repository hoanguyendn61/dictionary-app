package com.cuoiky.andoid.dictionaryapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Word implements Serializable {
    @SerializedName("word")
    private String word;
    @SerializedName("results")
    private List<Result> results;
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

    @Override
    public String toString() {
        return "Word{" +
                "\nword='" + word + '\'' +
                ",\n results=" + results +
                ",\n pronunciation=" + pronunciation +
                '}';
    }
}
