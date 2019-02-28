package com.hai.jedi.stackymaswaliflow.UI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.hai.jedi.stackymaswaliflow.Adapters.RecyclerViewAdapter;
import com.hai.jedi.stackymaswaliflow.Fragments.AnswerListFragment;
import com.hai.jedi.stackymaswaliflow.Interfaces.StackyInterface;
import com.hai.jedi.stackymaswaliflow.Models.Answers;
import com.hai.jedi.stackymaswaliflow.Models.Questions;
import com.hai.jedi.stackymaswaliflow.R;
import com.hai.jedi.stackymaswaliflow.Services.ListWrapper;
import com.hai.jedi.stackymaswaliflow.Services.StackService;
import com.hai.jedi.stackymaswaliflow.Utils.CustomLifeCycleOwner;
import com.hai.jedi.stackymaswaliflow.Utils.FakeDataProvider;
import com.hai.jedi.stackymaswaliflow.ViewModels.AnswerViewModel;
import com.hai.jedi.stackymaswaliflow.ViewModels.QuestionViewModels;

import java.util.ArrayList;
import java.util.List;

public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener{

    //FOR API CALL
    private StackyInterface stackOverflowCall;

    private String token;

    private Button authButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Grabbing the spinner.
        /*
        * Populating the spinner with questions and listening in on the question selected to display a
        * answer info.
        * */

        // Fetching the questions


        /*questionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Displaying answers for a giving question
                 question = (Questions) parent.getAdapter().getItem(position);
                //Log.d("CAN YOU SEE ME: ", String.valueOf(question));
                // updateAnswerListFragment(question.question_id);


                *//*Bundle info = new Bundle();
                info.putString("question_id", question.question_id);
                Log.d("CAN YOU SEE ME: ", String.valueOf(info));
                AnswerListFragment answerListFragment = new AnswerListFragment();
                answerListFragment.setArguments(info);
                Log.d("CAN YOU SEE ME: ", String.valueOf(answerListFragment));*//*

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        authButton = findViewById(R.id.authenticate_button);

    }


    @Override
    protected void onResume(){
        super.onResume();
        if(token !=null){
            authButton.setEnabled(false);
        }
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case android.R.id.text1:
                if(token != null){
                    Toast.makeText(this,
                            "Welcome",
                            Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(this,
                            "You need to authenticate first",
                            Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.authenticate_button:
                // TODO
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK && requestCode == -1){
            token = data.getStringExtra("token");
        }
    }

}
