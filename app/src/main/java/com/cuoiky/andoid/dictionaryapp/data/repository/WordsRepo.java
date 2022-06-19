package com.cuoiky.andoid.dictionaryapp.data.repository;


import android.app.Application;
import android.util.Log;

import com.cuoiky.andoid.dictionaryapp.data.local.AppDatabase;
import com.cuoiky.andoid.dictionaryapp.data.local.WordsDao;
import com.cuoiky.andoid.dictionaryapp.data.model.wordsapi.Word;
import com.cuoiky.andoid.dictionaryapp.data.remote.wordsapi.WordsApiService;
import com.cuoiky.andoid.dictionaryapp.util.WordResponseListener;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WordsRepo {
    private WordsDao mWordsDao;
    private static WordsRepo instance;
    private static final String TAG = "WordsRepo";
    private Word word;
    private WordsApiService wordsApiService = new WordsApiService();
    public static WordsRepo getInstance(Application app){
        if (instance == null){
            instance = new WordsRepo(app);
        }
        return instance;
    }
    private WordsRepo(Application app){
        AppDatabase appDatabase = AppDatabase.getInstance(app);
        this.mWordsDao = appDatabase.wordsDao();
    }
    //    find if word exists in list of favourite words
    public Single<Word> findWord(String w){
        return this.mWordsDao.findWord(w);
    }
    public void getWordFromApi(String w, WordResponseListener resultListener){
        Call<Word> wordResponse =  wordsApiService.getWord(w);
        word = null;
        wordResponse.enqueue(new Callback<Word>() {
            @Override
            public void onResponse(Call<Word> call, Response<Word> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG,"Code " + response.code());
                    resultListener.onFail("Code " + response.code());
                } else {
                    word = response.body();
                    resultListener.onSuccess(word);
                }
            }
            @Override
            public void onFailure(Call<Word> call, Throwable t) {
                Log.d(TAG,t.getMessage());
            }
        });
    }
}
