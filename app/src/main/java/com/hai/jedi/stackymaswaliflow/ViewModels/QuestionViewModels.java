package com.hai.jedi.stackymaswaliflow.ViewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Log;

import com.hai.jedi.stackymaswaliflow.Interfaces.StackyInterface;
import com.hai.jedi.stackymaswaliflow.Models.Answers;
import com.hai.jedi.stackymaswaliflow.Models.Questions;
import com.hai.jedi.stackymaswaliflow.Services.ListWrapper;
import com.hai.jedi.stackymaswaliflow.Services.StackService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionViewModels extends ViewModel {
    public static final String TAG = AnswerViewModel.class.getSimpleName();
    private MutableLiveData<ListWrapper<Questions>> question_list;
    private MutableLiveData<ListWrapper<Answers>> answer_list;
    private final MutableLiveData<Questions> selectedQuestion = new MutableLiveData<Questions>();

    public LiveData<ListWrapper<Questions>> getQuestionList () {
        if(question_list == null){
            question_list = new MutableLiveData<>();
            // Loading data asynchronously
            loadQuestions();
        }
        return question_list;
    }

    public MutableLiveData<Questions> getSelectedQuestion(){
        return selectedQuestion;
    }


    public LiveData<ListWrapper<Answers>> answersForQuestionSelected(Questions question){
        selectedQuestion.setValue(question);
        if(answer_list == null){
            answer_list = new MutableLiveData<>();
            // Loading data asynchronously
            loadAnswers(question);
        }
        return answer_list;

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
                   //Log.d("CAN YOU SEE ME", String.valueOf(questions));
                   assert questions != null;
                   question_list.setValue(response.body());


               } else {
                Log.d("QuestionViewModel", "Something went wrong");
               }

           }

           @Override
           public void onFailure(@NonNull Call<ListWrapper<Questions>> call,
                                 @NonNull Throwable exception) {
                exception.printStackTrace();
           }
       });
    }


    private void loadAnswers(Questions questions){
        StackyInterface stackApiCall = StackService.stackApiCall();
        Call<ListWrapper<Answers>> call = stackApiCall.getAnswersForQuestion(questions.question_id);


        call.enqueue(new Callback<ListWrapper<Answers>>(){
            @Override
            public void onResponse(@NonNull Call<ListWrapper<Answers>> call,
                                   @NonNull Response<ListWrapper<Answers>> response){
                if(response.isSuccessful()){
                    ListWrapper<Answers> answers = response.body();
                    Log.d(TAG.toUpperCase(), String.valueOf(answers));
                    assert answers != null;
                    answer_list.setValue(answers);
                } else {
                    Log.d(TAG.toUpperCase(),
                            String.format("Code: %s Message: %s", response.code(), response.body()));
                }

            }

            @Override
            public void onFailure(@NonNull Call<ListWrapper<Answers>> call,
                                  @NonNull Throwable exception){
                exception.printStackTrace();
            }
        });
    }

}
