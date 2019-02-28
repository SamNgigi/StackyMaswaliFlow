package com.hai.jedi.stackymaswaliflow.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hai.jedi.stackymaswaliflow.Adapters.RecyclerViewAdapter;
import com.hai.jedi.stackymaswaliflow.Models.Answers;
import com.hai.jedi.stackymaswaliflow.R;
import com.hai.jedi.stackymaswaliflow.Services.ListWrapper;
import com.hai.jedi.stackymaswaliflow.Utils.FakeDataProvider;
import com.hai.jedi.stackymaswaliflow.ViewModels.AnswerViewModel;
import com.hai.jedi.stackymaswaliflow.ViewModels.QuestionViewModels;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnswerListFragment extends Fragment {

    private static final String TAG = AnswerListFragment.class.getSimpleName().toUpperCase();

    @BindView(R.id.list) RecyclerView recyclerView;
    String question_id;

    private QuestionViewModels questionViewModels;

    public AnswerListFragment() {
        // Required empty public constructor
    }

    public static AnswerListFragment newInstance(String question_id){
        AnswerListFragment answerListFragment = new AnswerListFragment();
        Bundle args = new Bundle();
        args.putString("question_id", question_id);
        answerListFragment.setArguments(args);
        return answerListFragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstances){
        super.onActivityCreated(savedInstances);

        questionViewModels = ViewModelProviders.of(Objects.requireNonNull(this.getActivity()))
                .get(QuestionViewModels.class);

        questionViewModels.answersForQuestionSelected().observe(
                this, questions -> {
                    recyclerView.setAdapter(new RecyclerViewAdapter(questions.items));
                }
        );

        /*question_id = getArguments().getString(question_id);
        Log.d("CAN YOU SEE ME", question_id);*/
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_answer_list, container, false);
        ButterKnife.bind(this, view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;
    }

}
