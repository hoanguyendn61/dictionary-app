package com.cuoiky.andoid.dictionaryapp.data.model.wordquiz;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WordQuiz {
    @SerializedName("area")
    private String area;
    @SerializedName("level")
    private int level;
    @SerializedName("quizlist")
    private List<Quiz> quizList;

    public WordQuiz(String area, int level, List<Quiz> quizList) {
        this.area = area;
        this.level = level;
        this.quizList = quizList;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }

    @Override
    public String toString() {
        return "WordQuiz{" +
                "area='" + area + '\'' +
                ", level=" + level +
                ", quizList=" + quizList +
                '}';
    }
}
