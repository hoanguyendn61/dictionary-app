package com.cuoiky.andoid.dictionaryapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Word {
    @SerializedName("word")
    private String word;
    @SerializedName("results")
    private List<Result> results;
    @SerializedName("pronunciation")
    private String pronunciation;

    public Word(String word, List<Result> results, String pronunciation) {
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

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }
}
