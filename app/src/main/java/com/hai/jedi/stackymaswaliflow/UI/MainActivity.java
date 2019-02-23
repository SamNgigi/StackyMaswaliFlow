package com.hai.jedi.stackymaswaliflow.UI;

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
import com.hai.jedi.stackymaswaliflow.Utils.FakeDataProvider;

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

        questionSpinner = findViewById(R.id.questions_spinner);

        questionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Questions questions = (Questions) parent.getAdapter().getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        authButton = findViewById(R.id.authenticate_button);

        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        List<Answers> answers = FakeDataProvider.getAnswers();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(answers);
        recyclerView.setAdapter(adapter);

        // Preparing the Api request
        createStackOverflowApi();
        stackOverflowCall.getQuestions().enqueue(questionsCallback);
    }

    private void createStackOverflowApi(){
        Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                        .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StackyInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        stackOverflowCall = retrofit.create(StackyInterface.class);
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


    // Handling the response from the callback.
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

}
