package com.cuoiky.andoid.dictionaryapp.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.cuoiky.andoid.dictionaryapp.R;
import com.cuoiky.andoid.dictionaryapp.data.model.wordsapi.Result;
import com.google.android.flexbox.FlexboxLayout;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ViewHolder> {
    private final Context context;
    private List<Result> resultList = new ArrayList<>();
    public ResultsAdapter(Context context){
        this.context = context;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setResultsList(List<Result> listResult){
        this.resultList.clear();
        this.resultList = listResult;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ResultsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultsAdapter.ViewHolder holder, int position) {
        holder.bind(resultList.get(position), position+1, context);
    }

    @Override
    public int getItemCount() {
        return this.resultList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final AppCompatTextView definition;
        private final AppCompatTextView partOfSpeech;
        private final FlexboxLayout fl_synonyms;
        private final LinearLayout ll_examples;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.definition = (AppCompatTextView) itemView.findViewById(R.id.tv_definition);
            this.partOfSpeech = (AppCompatTextView) itemView.findViewById(R.id.tv_partOfSpeech);
            this.fl_synonyms = (FlexboxLayout) itemView.findViewById(R.id.fl_synonyms);
            this.ll_examples = (LinearLayout) itemView.findViewById(R.id.ll_examples);
        }
        public void bind(final Result item, int position,final Context context){
            String def = item.getDefinition();
            def = def.substring(0,1).toUpperCase(Locale.ROOT) + def.substring(1).toLowerCase(Locale.ROOT);
            definition.setText(def);
            String pos = item.getPartOfSpeech() == null ? "" : item.getPartOfSpeech();
            String idpos = position + ". "+ pos;
            partOfSpeech.setText(idpos);
            // null check synonyms
            if (item.getSynonyms() != null){
                fl_synonyms.removeAllViews();
                for (int i = 0; i< item.getSynonyms().size(); i++){
                    final TextView tv = new TextView(context);
                    tv.setText(item.getSynonyms().get(i));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tv.setTextAppearance(R.style.SynonymTextStyle);
                    } else {
                        tv.setTextAppearance(context, R.style.SynonymTextStyle);
                    }
                    tv.setPadding(10,5,10,5);
                    fl_synonyms.addView(tv);
                }
            }
            // null check examples
            if (item.getExamples() != null){
                ll_examples.removeAllViews();
                for (int i =0; i< item.getExamples().size(); i++){
                    final TextView tv = new TextView(context);
                    String temp = "⚬ " + item.getExamples().get(i);
                    tv.setText(temp);
                    ll_examples.addView(tv);
                }
            }
        }
    }
}
