package com.cuoiky.andoid.dictionaryapp.data.repository;


import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.cuoiky.andoid.dictionaryapp.data.local.AppDatabase;
import com.cuoiky.andoid.dictionaryapp.data.local.WordsDao;
import com.cuoiky.andoid.dictionaryapp.data.model.wordsapi.Word;
import com.cuoiky.andoid.dictionaryapp.data.remote.wordsapi.WordsApiService;
import com.cuoiky.andoid.dictionaryapp.ui.main.MainActivity;
import com.cuoiky.andoid.dictionaryapp.util.WordResponseListener;

import java.util.List;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WordsRepo {
    private WordsDao mWordsDao;
    private LiveData<List<Word>> mFavWords;
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
        this.mFavWords = mWordsDao.getAllFavouriteWords();
    }
    public LiveData<List<Word>> getFavWords(){
        return this.mFavWords;
    }

  // find if word exists in list of favourite words
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
                Log.d(TAG,"onFailure:" + t.getMessage());
            }
        });
    }
    public void insertWord(Word w){
        new insertAsyncTask(mWordsDao).execute(w);
    }
    public void removeWord(Word w){
        new removeAsyncTask(mWordsDao).execute(w);
    }
    public void removeListofWords(List<Word> w, MainActivity.OnRemoveSelectedWords mListener){
        new removeListAsyncTask(mListener, mWordsDao).execute(w);
    }
    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {
        private final WordsDao mAsyncTaskDao;
        insertAsyncTask(WordsDao dao){
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(Word... params) {
            mAsyncTaskDao.insertWord(params[0]);
            return null;
        }
    }
    private static class removeAsyncTask extends AsyncTask<Word, Void, Void> {
        private final WordsDao mAsyncTaskDao;
        removeAsyncTask(WordsDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.removeWord(params[0]);
            return null;
        }
    }
    private static class removeListAsyncTask extends AsyncTask<List<Word>, Void, Boolean>{
        private final MainActivity.OnRemoveSelectedWords mListener;
        private final WordsDao mAsyncTaskDao;
        removeListAsyncTask(MainActivity.OnRemoveSelectedWords mListener, WordsDao dao) {
            this.mListener = mListener;
            mAsyncTaskDao = dao;
        }

        @Override
        protected Boolean doInBackground(List<Word>... lists) {
            mAsyncTaskDao.removeListofWords(lists[0]);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (mListener!=null){
                mListener.clear(aBoolean);
            }
        }
    }
}
