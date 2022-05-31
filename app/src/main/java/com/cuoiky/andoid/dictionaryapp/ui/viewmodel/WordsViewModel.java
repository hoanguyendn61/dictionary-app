package com.cuoiky.andoid.dictionaryapp.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cuoiky.andoid.dictionaryapp.data.model.Word;
import com.cuoiky.andoid.dictionaryapp.data.repository.WordsRepo;
import com.cuoiky.andoid.dictionaryapp.util.WordResponseListener;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;

public class WordsViewModel extends ViewModel {
    private WordsRepo mRepo;
    public void init(@NonNull Application application){
        mRepo = mRepo.getInstance(application);
    }
    public void getWord(String w, WordResponseListener responseListener){
        mRepo.getWordFromApi(w, responseListener);
    }
}
