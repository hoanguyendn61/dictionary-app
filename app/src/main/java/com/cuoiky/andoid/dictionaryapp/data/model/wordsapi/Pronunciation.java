package com.cuoiky.andoid.dictionaryapp.data.model.wordsapi;

import com.google.gson.annotations.SerializedName;

public class Pronunciation {
    @SerializedName("all")
    private String all;
    @SerializedName("noun")
    private String noun;
    @SerializedName("verb")
    private String verb;

    public Pronunciation(String all, String noun, String verb) {
        this.all = all;
        this.noun = noun;
        this.verb = verb;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getNoun() {
        return noun;
    }

    public void setNoun(String noun) {
        this.noun = noun;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    @Override
    public String toString() {
        return "Pronunciation{" +
                "all='" + all + '\'' +
                ", noun='" + noun + '\'' +
                ", verb='" + verb + '\'' +
                '}';
    }
}
