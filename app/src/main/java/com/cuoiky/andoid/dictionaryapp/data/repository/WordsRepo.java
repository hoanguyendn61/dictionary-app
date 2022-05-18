package com.cuoiky.andoid.dictionaryapp.data.repository;


import android.app.Application;

import com.cuoiky.andoid.dictionaryapp.data.model.Word;
import com.cuoiky.andoid.dictionaryapp.data.remote.WordsApiService;

import retrofit2.Call;

public class WordsRepo {
    private static WordsRepo instance;
    private WordsApiService wordsApiService = new WordsApiService();
    public static WordsRepo getInstance(Application app){
        if (instance == null){
            instance = new WordsRepo(app);
        }
        return instance;
    }
    private WordsRepo(Application app){

    }
    public Call<Word> getWordFromApi(String w){
        return wordsApiService.getWord(w);
    }
}
