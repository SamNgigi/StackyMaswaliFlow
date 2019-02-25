package com.hai.jedi.stackymaswaliflow.Interfaces;

import com.hai.jedi.stackymaswaliflow.Constants;
import com.hai.jedi.stackymaswaliflow.Models.Answers;
import com.hai.jedi.stackymaswaliflow.Models.Questions;
import com.hai.jedi.stackymaswaliflow.Services.ListWrapper;


import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.Call;

public interface StackyInterface {
    String BASE_URL = Constants.STACK_BASE_URL;

    @GET(Constants.STACK_QUESTIONS_URL)
    Call<ListWrapper<Questions>> getQuestions();

    @GET(Constants.STACK_ANSWERS_URL)
    Call<ListWrapper<Answers>> getAnswersForQuestion(@Path("id") String question_id);

}
