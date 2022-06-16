package com.cuoiky.andoid.dictionaryapp.data.model.wordquiz;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Quiz {
    @SerializedName("correct")
    private int correct;
    @SerializedName("option")
    private List<String> option;
    @SerializedName("quiz")
    private List<String> quiz;

    public Quiz(int correct, List<String> option, List<String> quiz) {
        this.correct = correct;
        this.option = option;
        this.quiz = quiz;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public List<String> getOption() {
        return option;
    }

    public void setOption(List<String> option) {
        this.option = option;
    }

    public List<String> getQuiz() {
        return quiz;
    }

    public void setQuiz(List<String> quiz) {
        this.quiz = quiz;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "correct='" + correct + '\'' +
                ", option=" + option +
                ", quiz=" + quiz +
                '}';
    }
}
