package com.cuoiky.andoid.dictionaryapp.data.model.wordsapi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("definition")
    private String definition;
    @SerializedName("partOfSpeech")
    private String partOfSpeech;
    @SerializedName("examples")
    private List<String> examples;
    @SerializedName("synonyms")
    private List<String> synonyms;

    public Result(String definition, String partOfSpeech, List<String> examples, List<String> synonyms) {
        this.definition = definition;
        this.partOfSpeech = partOfSpeech;
        this.examples = examples;
        this.synonyms = synonyms;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public List<String> getExamples() {
        return examples;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    @Override
    public String toString() {
        return "\nResult{" +
                "definition='" + definition + '\'' +
                ", partOfSpeech='" + partOfSpeech + '\'' +
                ", examples=" + examples +
                ", synonyms=" + synonyms +
                '}';
    }
}
