package com.cuoiky.andoid.dictionaryapp.data.remote;

import com.cuoiky.andoid.dictionaryapp.data.model.Word;
import com.cuoiky.andoid.dictionaryapp.util.Constants;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WordsApiService {
    private WordsApi api;
    public WordsApiService(){
        api = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WordsApi.class);
    }
    public Call<Word> getWord(String w){
        return api.getWord(w);
    }
}
