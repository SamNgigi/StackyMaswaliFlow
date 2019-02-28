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
                 android.R.layout.simple_selectable_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int position){
        Answers answer = data.get(position);
        viewHolder.text.setText(answer.toString());
        viewHolder.itemView.setTag(answer.answer_id);
    }

    @Override
    public int getItemCount(){
        return data.size();
    }

    public void updateData(List<Answers> answers){
        this.data = answers;
        notifyDataSetChanged();
    }

}
