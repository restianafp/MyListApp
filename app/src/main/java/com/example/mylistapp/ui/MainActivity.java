package com.example.mylistapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mylistapp.R;
import com.example.mylistapp.model.ToDo;
import com.example.mylistapp.network.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ToDo> itemList;

    private ToDoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiClient client = ApiClient.getInstance();
        Call<List<ToDo>> call = client.getApiService().getAll();
        itemList = new ArrayList<>();

        call.enqueue(new Callback<List<ToDo>>() {

            @Override
            public void onResponse(Call<List<ToDo>> call, Response<List<ToDo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    for (ToDo toDo : response.body()) {
                        itemList.add(toDo);
                    }

                    recyclerView = findViewById(R.id.rv);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter = new ToDoAdapter(getApplicationContext(), itemList);
                    recyclerView.setAdapter(adapter);

                } else {
                    Toast.makeText(MainActivity.this, "Response error", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<ToDo>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Connection error", Toast.LENGTH_LONG).show();
            }
        });




    }
}