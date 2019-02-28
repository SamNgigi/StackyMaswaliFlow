package com.hai.jedi.stackymaswaliflow.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hai.jedi.stackymaswaliflow.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private String token;

    private Button authButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
