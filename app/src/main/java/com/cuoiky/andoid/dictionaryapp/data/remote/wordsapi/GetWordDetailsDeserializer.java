package com.cuoiky.andoid.dictionaryapp.data.remote.wordsapi;


import android.util.Log;

import com.cuoiky.andoid.dictionaryapp.data.model.wordsapi.Pronunciation;
import com.cuoiky.andoid.dictionaryapp.data.model.wordsapi.Result;
import com.cuoiky.andoid.dictionaryapp.data.model.wordsapi.Word;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetWordDetailsDeserializer implements JsonDeserializer<Word> {

    @Override
    public Word deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<Result> results = new ArrayList<Result>();
        final JsonObject jsonObject = json.getAsJsonObject();
        Log.d("Word", String.valueOf(jsonObject));
        final String word = jsonObject.get("word").getAsString();
        try {
            final JsonArray resultsJsonArray = jsonObject.get("results").getAsJsonArray();
            Gson gson = new Gson();
            for (JsonElement resultsJsonElement : resultsJsonArray){
                final JsonObject resultJsonObject  = resultsJsonElement.getAsJsonObject();
                final String def = resultJsonObject.get("definition").getAsString();
                String partOfSpeech = "";
                try {
                    final JsonElement partOfSpeech_el = resultJsonObject.get("partOfSpeech");
                    partOfSpeech = partOfSpeech_el.getAsString();
                } catch(Exception e){
                    partOfSpeech = "";
                }
                Type listStringType = new TypeToken<List<String>>() {}.getType();
                List<String> examples;
                List<String> synonyms;
                try {
                    final JsonArray examplesJsonArray = resultJsonObject.get("examples").getAsJsonArray();
                    examples = gson.fromJson(examplesJsonArray, listStringType);
                } catch (Exception e){
                    examples = null;
                }
                try {
                    final JsonArray synonymsJsonArray = resultJsonObject.get("synonyms").getAsJsonArray();
                    synonyms = gson.fromJson(synonymsJsonArray, listStringType);
                } catch (Exception e){
                    synonyms = null;
                }
                results.add(new Result(def, partOfSpeech, examples, synonyms));
            }
        } catch (Exception e){
            Log.d("Word", e.getMessage());
            results = null;
        }
        Pronunciation pron = null;
        JsonObject pronObject = new JsonObject();
        String all = null;
        String verb = null;
        String noun = null;
        try {
            pronObject = jsonObject.get("pronunciation").getAsJsonObject();
            final JsonElement all_el = pronObject.get("all");
            final JsonElement verb_el = pronObject.get("verb");
            final JsonElement noun_el = pronObject.get("noun");
            if (all_el!= null){
                all = all_el.getAsString();
            }
            if (verb_el != null){
                verb = verb_el.getAsString();
            }
            if (noun_el != null){
                noun = noun_el.getAsString();
            }
        } catch(Exception e){
            if (jsonObject.get("pronunciation") != null){
                all = jsonObject.get("pronunciation").getAsString();
            }
        }
        pron = new Pronunciation(all, noun, verb);
        Word w = new Word(word, results, pron);
        return w;
    }
}
