package com.cuoiky.andoid.dictionaryapp.data.remote.wordquiz;

import com.cuoiky.andoid.dictionaryapp.data.model.wordquiz.WordQuiz;
import com.cuoiky.andoid.dictionaryapp.util.Constants;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WordQuizApiService {
    private WordQuizApi api;
    public WordQuizApiService(){
        api = new Retrofit.Builder()
                .baseUrl(Constants.WORDQUIZ_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WordQuizApi.class);
    }
    public Call<WordQuiz> getQuiz(String level, String area){
        return api.getQuiz(level, area);
    }
}
