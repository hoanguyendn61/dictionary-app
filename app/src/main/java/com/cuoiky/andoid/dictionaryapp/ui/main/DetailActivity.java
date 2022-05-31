package com.cuoiky.andoid.dictionaryapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cuoiky.andoid.dictionaryapp.R;
import com.cuoiky.andoid.dictionaryapp.data.model.Word;
import com.cuoiky.andoid.dictionaryapp.databinding.ActivityDetailBinding;
import com.cuoiky.andoid.dictionaryapp.ui.adapter.ResultsAdapter;
import com.cuoiky.andoid.dictionaryapp.ui.viewmodel.WordsViewModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private final static String TAG = "DetailActivity";
    private ResultsAdapter mAdapter;
    private Word mWord;
    private WordsViewModel mViewModel;
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
        mViewModel = new ViewModelProvider(this).get(WordsViewModel.class);
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
        mAdapter.setResultsList(mWord.getResults());
    }
    
}