package com.hai.jedi.stackymaswaliflow.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.hai.jedi.stackymaswaliflow.Models.Questions;
import com.hai.jedi.stackymaswaliflow.R;
import com.hai.jedi.stackymaswaliflow.Utils.FakeDataProvider;

import java.util.List;

public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener{

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
                Toast.makeText(MainActivity.this,
                                "Spinner item selected",
                                 Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<Questions> questions = FakeDataProvider.getQuesttions();
        ArrayAdapter<Questions> questionArrayAdapter = new ArrayAdapter<Questions>(
          MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                questions
        );

        questionSpinner.setAdapter(questionArrayAdapter);

        authButton = findViewById(R.id.authenticate_button);
        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
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
