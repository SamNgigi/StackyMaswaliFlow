package com.hai.jedi.stackymaswaliflow.Interfaces;

import com.hai.jedi.stackymaswaliflow.Models.Answers;
import com.hai.jedi.stackymaswaliflow.Models.Questions;
import com.hai.jedi.stackymaswaliflow.Services.ListWrapper;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.Call;

public interface StackyInterface {
    String BASE_URL = "https://api.stackexchange.com";
    String c = "/2.2/questions?order=desc&sort=votes&site=stackoverflow&tagged=android&filter=withbody";

    @GET(c)
    Call<ListWrapper<Questions>> getQuestions();

    @GET("/2.2/questions/{id}answers?order=desc&sort=votes&site=stackoverflow")
    Call<ListWrapper<Answers>> getAnswersForQuestion(@Path("id") String question_id);

}
