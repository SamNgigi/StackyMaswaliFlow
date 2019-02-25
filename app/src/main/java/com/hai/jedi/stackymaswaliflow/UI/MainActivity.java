package com.hai.jedi.stackymaswaliflow.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.hai.jedi.stackymaswaliflow.Interfaces.StackyInterface;
import com.hai.jedi.stackymaswaliflow.Models.Answers;
import com.hai.jedi.stackymaswaliflow.Models.Questions;
import com.hai.jedi.stackymaswaliflow.R;
import com.hai.jedi.stackymaswaliflow.Services.ListWrapper;
import com.hai.jedi.stackymaswaliflow.Services.StackService;
import com.hai.jedi.stackymaswaliflow.Utils.FakeDataProvider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener{

    //FOR API CALL
    private StackyInterface stackOverflowCall;

    private String token;

    private Button authButton;

    private Spinner questionSpinner;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initializing our Api Call to stackOverflowCall
        stackOverflowCall= StackService.stackApiCall();
        questionSpinner = findViewById(R.id.questions_spinner);


        questionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Displaying answers for a giving question
                Questions question = (Questions) parent.getAdapter().getItem(position);
                Log.d("CAN YOU SEE ME: ", String.valueOf(question));
                // Fetching the answers for a given question
                stackOverflowCall.getAnswersForQuestion(question.question_id)
                                 .enqueue(answersCallback);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        authButton = findViewById(R.id.authenticate_button);

        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        // Fetching the questions
        stackOverflowCall.getQuestions().enqueue(questionsCallback);
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


    // Handling the question api call and response.
    Callback<ListWrapper<Questions>> questionsCallback = new Callback<ListWrapper<Questions>>(){
        @Override
        public void onResponse(Call<ListWrapper<Questions>> call,
                               Response<ListWrapper<Questions>> response){
            if(response.isSuccessful()){
                ListWrapper<Questions> questions = response.body();
                Log.d("CAN YOU SEE ME", String.valueOf(questions));
                assert questions != null;
                ArrayAdapter<Questions> arrayAdapter = new ArrayAdapter<Questions>(
                        MainActivity.this,
                        android.R.layout.simple_spinner_dropdown_item, questions.items
                );
                questionSpinner.setAdapter(arrayAdapter);
            } else {
                Log.d("QUESTIONS_CALLBACK",
                        String.format("Code: %s Message: %s", response.code(), response.body()));
            }
        }

        @Override
        public void onFailure(Call<ListWrapper<Questions>> call, Throwable exception){
            exception.printStackTrace();
        }
    };

    // Handling the get answers api call and response
    Callback<ListWrapper<Answers>> answersCallback = new Callback<ListWrapper<Answers>>(){
        @Override
        public void onResponse(@NonNull Call<ListWrapper<Answers>> call,
                               @NonNull Response<ListWrapper<Answers>>response){
            if(response.isSuccessful()){
                assert response.body() != null;
                List<Answers> data = new ArrayList<>(response.body().items);
                Log.d("CAN YOU SEE ME", String.valueOf(data));
                recyclerView.setAdapter(new RecyclerViewAdapter(data));
            } else {
                Log.d("ANSWERS CALL",
                        String.format("Code: %s Message: %s", response.code(), response.body()));
            }
        }

        @Override
        public void onFailure(Call<ListWrapper<Answers>> call, Throwable exception){
            exception.printStackTrace();
        }
    };

}
