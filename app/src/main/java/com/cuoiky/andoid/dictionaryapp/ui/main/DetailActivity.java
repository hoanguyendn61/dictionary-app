package com.cuoiky.andoid.dictionaryapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cuoiky.andoid.dictionaryapp.data.model.wordsapi.Word;
import com.cuoiky.andoid.dictionaryapp.databinding.ActivityDetailBinding;
import com.cuoiky.andoid.dictionaryapp.ui.adapter.ResultsAdapter;
import com.cuoiky.andoid.dictionaryapp.ui.viewmodel.MainViewModel;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private final static String TAG = "DetailActivity";
    private ResultsAdapter mAdapter;
    private Word mWord;
    private MainViewModel mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);
        try{
            initValues();
        } catch (Exception e){
            Log.e(TAG, e.getMessage());
        }
    }
    void initValues(){
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.init(getApplication());
        Gson gson = new Gson();
        Intent i = getIntent();
        mWord = gson.fromJson(i.getStringExtra("wordItem"), Word.class);
        Log.d(TAG, mWord.toString());
        mAdapter = new ResultsAdapter(getApplicationContext());
        setData(mWord);
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    void setData(Word w){
        binding.tvWord.setText(w.getWord());
        RecyclerView.LayoutManager linearLayout = new LinearLayoutManager(getApplicationContext());
        binding.rvResults.setLayoutManager(linearLayout);
        binding.rvResults.setAdapter(mAdapter);
//        mAdapter.setResultsList(mWord.getResults());
    }
    
}