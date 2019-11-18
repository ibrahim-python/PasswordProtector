package com.example.passwordprotector;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.passwordprotector.database1.PassWord;

import java.util.ArrayList;
import java.util.List;

public class PassWordListAdapter extends RecyclerView.Adapter<PassWordListAdapter.WordViewHolder> {



    private List<PassWord> mPassWords = new ArrayList<>();
    private OnItemClickListener mItemClickListener;



    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        PassWord current = mPassWords.get(position);
        holder.passwordView.setText(current.getUsername());

    }

    public List<PassWord> getmWords(){
        return mPassWords;
    }

    @Override
    public int getItemCount() {
        return mPassWords.size();
    }

    void setWords(List<PassWord> passwords){
        this.mPassWords = passwords;
        notifyDataSetChanged();
    }

    public PassWord getPassWordAt(int position) {
        return mPassWords.get(position);
    }

    class WordViewHolder extends RecyclerView.ViewHolder {

        TextView passwordView;


        /**
         * Constructor for the TaskViewHolders.
         *
         * @param itemView The view inflated in onCreateViewHolder
         */
        public WordViewHolder(View itemView) {
            super(itemView);

            passwordView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (mItemClickListener != null && position != RecyclerView.NO_POSITION) {
                        mItemClickListener.onItemClick(mPassWords.get(position));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(PassWord password);
    }

    public void setOnItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


}

