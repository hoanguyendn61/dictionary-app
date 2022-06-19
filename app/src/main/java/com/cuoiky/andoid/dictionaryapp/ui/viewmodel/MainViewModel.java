package com.cuoiky.andoid.dictionaryapp.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.cuoiky.andoid.dictionaryapp.data.model.wordsapi.Word;
import com.cuoiky.andoid.dictionaryapp.data.repository.WordsRepo;
import com.cuoiky.andoid.dictionaryapp.util.WordResponseListener;

import io.reactivex.rxjava3.core.Single;

public class MainViewModel extends ViewModel {
    private WordsRepo mRepo;
    public void init(@NonNull Application application){
        mRepo = WordsRepo.getInstance(application);
    }
    public void getWord(String w, WordResponseListener responseListener){
        mRepo.getWordFromApi(w, responseListener);
    }
    public Single<Word> findWord(String w){
        return mRepo.findWord(w);
    }

}