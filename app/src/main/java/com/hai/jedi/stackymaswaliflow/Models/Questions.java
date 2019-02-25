package com.hai.jedi.stackymaswaliflow.Models;

import com.google.gson.annotations.SerializedName;

public class Questions {
    /*
    * We form this model based on how our Stack overflow Question format.
    * */
    public String title;
    public String body;

    @SerializedName("question_id")
    public String question_id;

    // Overriding original toString method.
    @Override
    public String toString(){
        return (title);
    }

}
