package com.cuoiky.andoid.dictionaryapp.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuoiky.andoid.dictionaryapp.R;
import com.cuoiky.andoid.dictionaryapp.data.model.wordsapi.Word;

import java.util.ArrayList;
import java.util.List;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.ViewHolder> {
    private final Context context;
    private List<Word> listFavWords = new ArrayList<>();
    private List<Word> selectedWords = new ArrayList<>();
    public interface OnItemClickListener{
        void onItemClick(Word item);
    }
    private final OnItemClickListener clickListener;

    public FavouritesAdapter(Context context,OnItemClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setFavWordsList(List<Word> list){
        this.listFavWords.clear();
        this.listFavWords = list;

        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(listFavWords.get(position), context, clickListener);
        Word w = listFavWords.get(position);
        if (!selectedWords.contains(w)){
            holder.word.setBackgroundResource(R.drawable.rounded_corner);
            holder.word.setTextColor(Color.BLACK);
        } else {
            holder.word.setBackgroundResource(R.drawable.dark_rounded_corner);
            holder.word.setTextColor(Color.WHITE);
        }
        holder.ll_word.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (!selectedWords.contains(w)){
                    holder.word.setBackgroundResource(R.drawable.dark_rounded_corner);
                    holder.word.setTextColor(Color.WHITE);
                    selectedWords.add(w);
                } else {
                    holder.word.setBackgroundResource(R.drawable.rounded_corner);
                    holder.word.setTextColor(Color.BLACK);
                    selectedWords.remove(w);
                }
                return true;
            }
        });
    }
    public List<Word> getSelectedWords(){
        return this.selectedWords;
    }
    public void clearSelectedWords(){
        this.selectedWords.clear();
    }
    @Override
    public int getItemCount() {
        return this.listFavWords.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView word;
        public final View ll_word;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.word = (TextView) itemView.findViewById(R.id.tv_favWord);
            this.ll_word = itemView.findViewById(R.id.ll_favWord);
        }
        public void bind(final Word item, final Context context, final OnItemClickListener clickListener){
            word.setText(item.getWord());
            ll_word.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(item);
                }
            });
        }
    }
}
