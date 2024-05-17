package com.example.mylistapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylistapp.R;
import com.example.mylistapp.model.ToDo;
import com.example.mylistapp.network.ApiClient;
import com.google.android.material.appbar.MaterialToolbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_class);

        MaterialToolbar toolbar = findViewById(R.id.appBar);
        toolbar.setOnClickListener(view -> finish());

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        ApiClient client = ApiClient.getInstance();
        Call<ToDo> call = client.getApiService().getTodoById(id);

        call.enqueue(new Callback<ToDo>() {

            @Override
            public void onResponse(Call<ToDo> call, Response<ToDo> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ToDo toDo = response.body();
                    TextView idTv = findViewById(R.id.idTv);
                    TextView userIdTv = findViewById(R.id.userIdTv);
                    TextView todoTv = findViewById(R.id.titleTv);
                    TextView completedTv = findViewById(R.id.statusTv);

                    userIdTv.setText("User ID " + toDo.getUserId());
                    todoTv.setText(toDo.getTitle());
                    idTv.setText("To Do ID " + toDo.getId());
                    if (toDo.getCompleted()){
                        completedTv.setText("Completed");
                        idTv.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.teal_700));
                    }else{
                        completedTv.setText("Not Completed");
                        idTv.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
                    }

                } else {
                    Toast.makeText(DetailClass.this, "Response error", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<ToDo> call, @NonNull Throwable t) {
                Toast.makeText(DetailClass.this, "Connection error", Toast.LENGTH_LONG).show();
            }
        });
    }
}