package com.cuoiky.andoid.dictionaryapp.data.remote.wordquiz;

import com.cuoiky.andoid.dictionaryapp.data.model.wordquiz.WordQuiz;
import com.cuoiky.andoid.dictionaryapp.util.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface WordQuizApi {
    @Headers({
            "X-RapidAPI-Host: " + Constants.WORDQUIZ_X_RAPID_API_HOST,
            "X-RapidAPI-Key: " + Constants.X_RAPID_API_KEY,
    })
    @GET("type1/")
    Call<WordQuiz> getQuiz(@Query("level") String level, @Query("area") String area);
}
