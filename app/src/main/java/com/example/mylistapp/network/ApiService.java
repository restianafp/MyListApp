package com.example.mylistapp.network;

import com.example.mylistapp.model.ToDo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("todos")
    Call<List<ToDo>> getAll();

    @GET("todos/{id}")
    Call<ToDo> getTodoById(@Path("id") int id);
}
