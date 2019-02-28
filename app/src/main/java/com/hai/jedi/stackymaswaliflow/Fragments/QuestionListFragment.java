package com.hai.jedi.stackymaswaliflow.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.hai.jedi.stackymaswaliflow.Models.Questions;
import com.hai.jedi.stackymaswaliflow.R;
import com.hai.jedi.stackymaswaliflow.Services.ListWrapper;
import com.hai.jedi.stackymaswaliflow.ViewModels.SharedViewModel;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionListFragment extends Fragment {

    @BindView(R.id.questions_spinner)
    Spinner  questionSpinner;
    private Questions question;
    private SharedViewModel sharedViewModel;

    public QuestionListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        sharedViewModel = ViewModelProviders.of(Objects.requireNonNull(this.getActivity()))
                .get(SharedViewModel.class);

        sharedViewModel.getQuestionList().observe(
                this, new Observer<ListWrapper<Questions>>(){
                    @Override
                    public void onChanged(ListWrapper<Questions> questionsListWrapper){
                        ArrayAdapter<Questions> questionsArrayAdapter = new ArrayAdapter<Questions>(
                                Objects.requireNonNull(getActivity()),
                                android.R.layout.simple_spinner_dropdown_item,
                                questionsListWrapper.items
                        );

                        questionSpinner.setAdapter(questionsArrayAdapter);
                    }
                });

        questionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Displaying answers for a giving question
                question = (Questions) parent.getAdapter().getItem(position);
                //Log.d("CAN YOU SEE ME: ", String.valueOf(question));
                sharedViewModel.selectedQuestion(question.question_id);
                sharedViewModel.loadAnswers(question.question_id);
                // updateAnswerListFragment(question.question_id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_list, container, false);
        ButterKnife.bind(this, view);
        // Here is how we bind without using Butterknife in a fragment
        // questionSpinner = (Spinner) view.findViewById(R.id.questionSpinner);
        return view;
    }

}
