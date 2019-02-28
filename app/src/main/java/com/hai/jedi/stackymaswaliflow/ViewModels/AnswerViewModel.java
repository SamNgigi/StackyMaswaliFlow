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

public class AnswerViewModel extends ViewModel{
    public static final String TAG = AnswerViewModel.class.getSimpleName();
    private MutableLiveData<ListWrapper<Answers>> answer_list;

    public LiveData<ListWrapper<Answers>> getAnswerList(){
        if(answer_list == null){
            answer_list = new MutableLiveData<>();
            // Loading questions asynchronously
            // loadAnswers();
        }
        return answer_list;
    }

}
