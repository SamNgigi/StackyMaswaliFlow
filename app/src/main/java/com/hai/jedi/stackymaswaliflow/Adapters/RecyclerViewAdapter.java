package com.hai.jedi.stackymaswaliflow.Adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;

import com.hai.jedi.stackymaswaliflow.Models.Answers;

import java.util.List;

public class RecyclerViewAdapter
        extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Answers> data;

    public RecyclerViewAdapter(List<Answers> data){
        this.data = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;

        public ViewHolder(View view){
            super(view);
            text = view.findViewById(android.R.id.text1);
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(
                 android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder viewHolder, int position){
        Answers answer = data.get(position);
        viewHolder.text.setText(answer.toString());
    }

    @Override
    public int getItemCount(){
        return data.size();
    }



}
