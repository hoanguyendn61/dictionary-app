package com.cuoiky.andoid.dictionaryapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cuoiky.andoid.dictionaryapp.R;
import com.cuoiky.andoid.dictionaryapp.data.model.wordquiz.Quiz;
import com.cuoiky.andoid.dictionaryapp.data.model.wordquiz.WordQuiz;
import com.cuoiky.andoid.dictionaryapp.data.model.wordsapi.Word;
import com.cuoiky.andoid.dictionaryapp.databinding.ActivityMainBinding;
import com.cuoiky.andoid.dictionaryapp.databinding.ActivityQuizBinding;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.util.List;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity{
    private ActivityQuizBinding binding;
    private final String TAG = "QuizActivity";
    int currentQuizIndex = 0;
    int totalQuiz;
    int score = 0;
    private WordQuiz mQuiz;
    private List<Quiz> quizList;
    private List<String> quizzes;
    private List<String> options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);
        try {
            initValues();
        } catch(Exception e){
            Log.e(TAG,e.toString());
        }
    }
    void initValues(){
        Gson gson = new Gson();
        Intent i = getIntent();
        mQuiz = gson.fromJson(i.getStringExtra("quiz"), WordQuiz.class);
        quizList = mQuiz.getQuizList();
        totalQuiz = quizList.size();
        loadNewQuiz();
        binding.btnOpt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(binding.btnOpt1);
            }
        });
        binding.btnOpt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(binding.btnOpt2);
            }
        });
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCorrect){
                    score++;
                }
                currentQuizIndex++;
                loadNewQuiz();
            }
        });
    }
    Boolean isCorrect = false;
    void checkAnswer(View view){
        MaterialButton btn = (MaterialButton) view;
        String selected = btn.getText().toString().toLowerCase(Locale.ROOT);
        int correct = quizList.get(currentQuizIndex).getCorrect();
        String answer = options.get(correct-1).toLowerCase(Locale.ROOT);
        if (selected.equals(answer)){
            isCorrect = true;
            btn.setBackgroundColor(getResources().getColor(R.color.success));
        } else{
            isCorrect = false;
            btn.setBackgroundColor(getResources().getColor(R.color.danger));
        }
        btn.setTextColor(Color.WHITE);
        binding.btnOpt1.setEnabled(false);
        binding.btnOpt2.setEnabled(false);
    }
    void loadNewQuiz(){
        if(currentQuizIndex == totalQuiz){
            finishQuiz();
            return;
        }
        String count = String.valueOf(currentQuizIndex+1) + "/" + totalQuiz;
        binding.tvCount.setText(count);
        quizzes = quizList.get(currentQuizIndex).getQuiz();
        options = quizList.get(currentQuizIndex).getOption();
        binding.btnOpt1.setTextColor(Color.BLACK);
        binding.btnOpt1.setBackgroundColor(Color.WHITE);
        binding.btnOpt2.setTextColor(Color.BLACK);
        binding.btnOpt2.setBackgroundColor(Color.WHITE);
        binding.tvQuiz1.setText(quizzes.get(0));
        binding.tvQuiz2.setText(quizzes.get(1));
        binding.tvQuiz3.setText(quizzes.get(2));
        binding.btnOpt1.setText(options.get(0));
        binding.btnOpt2.setText(options.get(1));
        binding.btnOpt1.setEnabled(true);
        binding.btnOpt2.setEnabled(true);
    }
    void finishQuiz(){
        new AlertDialog.Builder(this)
                .setTitle("Your results")
                .setMessage(score +" out of "+ totalQuiz)
                .setPositiveButton("OK",(dialogInterface, i) -> resetQuiz() )
                .setCancelable(false)
                .show();
    }
    void resetQuiz(){
        this.finish();
    }
}