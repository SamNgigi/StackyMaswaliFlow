package com.hai.jedi.stackymaswaliflow.ViewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Log;
import android.widget.Toast;

import com.hai.jedi.stackymaswaliflow.Interfaces.StackyInterface;
import com.hai.jedi.stackymaswaliflow.Models.Questions;
import com.hai.jedi.stackymaswaliflow.Services.ListWrapper;
import com.hai.jedi.stackymaswaliflow.Services.StackService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionViewModels extends ViewModel {
    private MutableLiveData<ListWrapper<Questions>> question_list;

    public LiveData<ListWrapper<Questions>> getQuestionList () {
        if(question_list == null){
            question_list = new MutableLiveData<>();
            // Loading data asynchronously
            loadQuestions();
        }
        return question_list;
    }

    private void loadQuestions(){
       StackyInterface stackApiCall =  StackService.stackApiCall();
       Call<ListWrapper<Questions>> call = stackApiCall.getQuestions();

       call.enqueue(new Callback<ListWrapper<Questions>>() {
           @Override
           public void onResponse(@NonNull Call<ListWrapper<Questions>> call,
                                  @NonNull Response<ListWrapper<Questions>> response) {

               if(response.isSuccessful()){
                   ListWrapper<Questions> questions = response.body();
                   Log.d("CAN YOU SEE ME", String.valueOf(questions));
                   assert questions != null;
                   question_list.setValue(response.body());


               } else {
                   Log.d("QUESTIONS_CALLBACK",
                           String.format("Code: %s Message: %s", response.code(), response.body()));
               }

           }

           @Override
           public void onFailure(@NonNull Call<ListWrapper<Questions>> call,
                                 @NonNull Throwable exception) {
                exception.printStackTrace();
           }
       });
    }

}
