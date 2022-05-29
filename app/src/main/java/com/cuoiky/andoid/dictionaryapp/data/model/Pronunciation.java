package com.cuoiky.andoid.dictionaryapp.data.model;

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

    @Override
    public String toString() {
        return "Pronunciation{" +
                "all='" + all + '\'' +
                ", noun='" + noun + '\'' +
                ", verb='" + verb + '\'' +
                '}';
    }
}
