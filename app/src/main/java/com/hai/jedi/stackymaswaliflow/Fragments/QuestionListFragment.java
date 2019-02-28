package com.hai.jedi.stackymaswaliflow.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.hai.jedi.stackymaswaliflow.Models.Questions;
import com.hai.jedi.stackymaswaliflow.R;
import com.hai.jedi.stackymaswaliflow.Services.ListWrapper;
import com.hai.jedi.stackymaswaliflow.UI.MainActivity;
import com.hai.jedi.stackymaswaliflow.ViewModels.QuestionViewModels;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionListFragment extends Fragment {

    @BindView(R.id.questions_spinner)
    Spinner  questionSpinner;

    public QuestionListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_list, container, false);
        ButterKnife.bind(this, view);

        QuestionViewModels questionViewModel = ViewModelProviders.of(this)
                .get(QuestionViewModels.class);

        questionViewModel.getQuestionList().observe(
                this, new Observer<ListWrapper<Questions>>(){
                    @Override
                    public void onChanged(ListWrapper<Questions> questionsListWrapper){
                        ArrayAdapter<Questions> questionsArrayAdapter = new ArrayAdapter<Questions>(
                                Objects.requireNonNull(getContext()),
                                android.R.layout.simple_spinner_dropdown_item,
                                questionsListWrapper.items
                        );

                        questionSpinner.setAdapter(questionsArrayAdapter);
                    }
                });

        return view;
    }

}
