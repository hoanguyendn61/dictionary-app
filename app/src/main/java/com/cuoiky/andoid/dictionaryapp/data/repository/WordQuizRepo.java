package com.cuoiky.andoid.dictionaryapp.data.repository;

import android.util.Log;

import com.cuoiky.andoid.dictionaryapp.data.model.wordquiz.WordQuiz;
import com.cuoiky.andoid.dictionaryapp.data.remote.wordquiz.WordQuizApiService;
import com.cuoiky.andoid.dictionaryapp.util.QuizResponseListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WordQuizRepo {
    private static WordQuizRepo instance;
    private static final String TAG = "WordQuizRepo";
    private WordQuizApiService wordQuizApiService = new WordQuizApiService();
    private WordQuiz wordQuiz;
    public static WordQuizRepo getInstance(){
        if(instance == null){
            instance = new WordQuizRepo();
        }
        return instance;
    }
    public void getWordQuizFromApi(String level, String area, QuizResponseListener quizListener){
        Call<WordQuiz> wordQuizCall = wordQuizApiService.getQuiz(level, area);
        wordQuizCall.enqueue(new Callback<WordQuiz>() {
            @Override
            public void onResponse(Call<WordQuiz> call, Response<WordQuiz> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG,"Code " + response.code());
                    quizListener.onFail("Code " + response.code());
                } else {
                    wordQuiz = response.body();
                    quizListener.onSuccess(wordQuiz);
                }
            }

            @Override
            public void onFailure(Call<WordQuiz> call, Throwable t) {
                Log.d(TAG,"onFailure:" + t.getMessage());
            }
        });
    }
}
