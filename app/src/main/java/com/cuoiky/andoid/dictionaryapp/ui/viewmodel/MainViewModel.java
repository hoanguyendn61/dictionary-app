package com.cuoiky.andoid.dictionaryapp.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cuoiky.andoid.dictionaryapp.data.model.wordsapi.Word;
import com.cuoiky.andoid.dictionaryapp.data.repository.WordsRepo;
import com.cuoiky.andoid.dictionaryapp.ui.main.MainActivity;
import com.cuoiky.andoid.dictionaryapp.util.WordResponseListener;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class MainViewModel extends ViewModel {
    private WordsRepo mRepo;
    private LiveData<List<Word>> mFavWords;
    public void init(@NonNull Application application){
        mRepo = WordsRepo.getInstance(application);
        mFavWords = mRepo.getFavWords();
    }
    public void getWord(String w, WordResponseListener responseListener){
        mRepo.getWordFromApi(w, responseListener);
    }
    public LiveData<List<Word>> getFavWords(){
        return this.mFavWords;
    }
    public Single<Word> findWord(String w){
        return mRepo.findWord(w);
    }
    public void insertWord(Word w){
        mRepo.insertWord(w);
    }
    public void removeWord(Word w){
        mRepo.removeWord(w);
    }
    public void removeListofWords( List<Word> w, MainActivity.OnRemoveSelectedWords mListener){
        mRepo.removeListofWords(w, mListener);
    }

}