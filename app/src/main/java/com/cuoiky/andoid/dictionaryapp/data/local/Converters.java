package com.cuoiky.andoid.dictionaryapp.data.local;

import androidx.room.TypeConverter;

import com.cuoiky.andoid.dictionaryapp.data.model.wordsapi.Pronunciation;
import com.cuoiky.andoid.dictionaryapp.data.model.wordsapi.Result;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Converters {
    @TypeConverter
    public static List<Result> toList(String value) {
        Type listType = new TypeToken<List<Result>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(List<Result> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
    @TypeConverter
    public static Pronunciation toPron(String value) {
        Type pronType = new TypeToken<Pronunciation>() {}.getType();
        return new Gson().fromJson(value, pronType);
    }

    @TypeConverter
    public static String fromPron(Pronunciation pron) {
        Gson gson = new Gson();
        String json = gson.toJson(pron);
        return json;
    }
}