package com.cuoiky.andoid.dictionaryapp.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.cuoiky.andoid.dictionaryapp.data.model.Word;
import com.cuoiky.andoid.dictionaryapp.data.repository.WordsRepo;

import retrofit2.Call;

public class WordsViewModel extends ViewModel {
    private WordsRepo mRepo;
    public void init(@NonNull Application application){
        mRepo = mRepo.getInstance(application);
    }
    public Call<Word> getWord(String w){
        return mRepo.getWordFromApi(w);
    }
}
