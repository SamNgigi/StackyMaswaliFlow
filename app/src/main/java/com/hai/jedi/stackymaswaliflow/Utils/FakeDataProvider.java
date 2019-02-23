package com.hai.jedi.stackymaswaliflow.Utils;

import com.hai.jedi.stackymaswaliflow.Models.Questions;

import java.util.ArrayList;
import java.util.List;

public class FakeDataProvider {

    public static List<Questions> getQuesttions(){
        List<Questions> questions = new ArrayList<>();
        for (int i=0; i<10; i++){
            Questions question = new Questions();
            // Question object
            question.question_id = String.valueOf(i);
            question.title = String.valueOf(i) + "TITLE";
            question.body = String.valueOf(i) + "Body";

            questions.add(question);
        }
        return questions;
    }

}
