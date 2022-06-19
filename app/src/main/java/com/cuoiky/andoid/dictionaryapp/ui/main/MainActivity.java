package com.cuoiky.andoid.dictionaryapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cuoiky.andoid.dictionaryapp.R;
import com.cuoiky.andoid.dictionaryapp.data.model.wordsapi.Word;
import com.cuoiky.andoid.dictionaryapp.databinding.ActivityMainBinding;
import com.cuoiky.andoid.dictionaryapp.ui.adapter.FavouritesAdapter;
import com.cuoiky.andoid.dictionaryapp.ui.viewmodel.MainViewModel;
import com.cuoiky.andoid.dictionaryapp.util.WordResponseListener;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private static final String TAG = "MainActivity";
    private MainViewModel mViewModel;
    private FavouritesAdapter mFavAdapter;
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
        createFavouritesRV();
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
    void createFavouritesRV(){
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getApplicationContext());
        binding.rvFavourites.setLayoutManager(layoutManager);
        mFavAdapter = new FavouritesAdapter(getApplicationContext(),
                new FavouritesAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Word item) {
                        openDetailActivity(item, true);
                    }
                });
        binding.rvFavourites.setAdapter(mFavAdapter);
        mViewModel.getFavWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                if(words != null){
                    mFavAdapter.setFavWordsList(words);
                } else {
                    Log.d(TAG, "words == null");
                }
            }
        });
    }
    void getWordFromApi(String text){
        mViewModel.getWord(text, new WordResponseListener(){
            @Override
            public void onSuccess(Word word) {
                if (word.getResults() != null){
                    try {
                        openDetailActivity(word, false);
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
    void openDetailActivity(Word word, Boolean isFav){
        Gson gson = new Gson();
        String myJson = gson.toJson(word);
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        intent.putExtra("wordItem", myJson);
        if (isFav){
            intent.putExtra("favourite", true);
        }
        startActivity(intent);
    }
}