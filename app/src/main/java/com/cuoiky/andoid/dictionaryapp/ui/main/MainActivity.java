package com.cuoiky.andoid.dictionaryapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cuoiky.andoid.dictionaryapp.R;
import com.cuoiky.andoid.dictionaryapp.data.model.wordsapi.Word;
import com.cuoiky.andoid.dictionaryapp.databinding.ActivityMainBinding;
import com.cuoiky.andoid.dictionaryapp.ui.viewmodel.MainViewModel;
import com.cuoiky.andoid.dictionaryapp.util.WordResponseListener;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private static final String TAG = "MainActivity";
    private MainViewModel mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);
        try {
            initValues();
        } catch(Exception e){
            Log.e(TAG,e.toString());
        }
    }
    private void initValues(){
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.init(getApplication());
        createSearchBarEvent();
    }
    void createSearchBarEvent(){
        binding.svSearchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getWordFromApi(query);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
    void getWordFromApi(String text){
        mViewModel.getWord(text, new WordResponseListener(){
            @Override
            public void onSuccess(Word word) {
                if (word.getResults() != null){
                    try {
                        Log.d(TAG, String.valueOf(word));
                    } catch(Exception e){
                        Log.e(TAG, e.toString());
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Word not found", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFail(String s) {
                Log.d(TAG,s);
                Toast.makeText(getApplicationContext(),"Word not found", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onError(Throwable t) {
                Log.e(TAG,t.getMessage());
                Toast.makeText(getApplicationContext(),"Error " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}