package com.cuoiky.andoid.dictionaryapp.util;

import com.cuoiky.andoid.dictionaryapp.data.model.wordsapi.Word;

public interface WordResponseListener {
    public void onSuccess(Word word);
    public void onFail(String e);
    public void onError(Throwable t);
}
