package com.cuoiky.andoid.dictionaryapp.util;

import com.cuoiky.andoid.dictionaryapp.data.model.wordquiz.WordQuiz;

public interface QuizResponseListener {
    public void onSuccess(WordQuiz word);
    public void onFail(String e);
    public void onError(Throwable t);
}
