package com.example.mylistapp.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylistapp.R;
import com.example.mylistapp.model.ToDo;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {

    private List<ToDo> itemList;
    private Context context;

    public ToDoAdapter(Context context, List<ToDo> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ToDo item = itemList.get(position);
        if (item.getCompleted()) {
            holder.tvIsCompleted.setText("Completed");
            holder.tvIsCompleted.setBackgroundColor(ContextCompat.getColor(context, R.color.teal_200));
        } else {
            holder.tvIsCompleted.setText("Not completed");
            holder.tvIsCompleted.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        }
        holder.tvId.setText("To Do " + item.getId());
        holder.tvUserId.setText("User ID " + item.getUserId());
        holder.tvTodo.setText(item.getTitle());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailClass.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (item != null){
                intent.putExtra("id", item.getId());
            }
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvIsCompleted;
        public TextView tvId;
        public TextView tvUserId;
        public TextView tvTodo;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvIsCompleted = itemView.findViewById(R.id.tvIsCompleted);
            tvId = itemView.findViewById(R.id.tvId);
            tvUserId = itemView.findViewById(R.id.tvUserId);
            tvTodo = itemView.findViewById(R.id.tvToDo);
        }

    }
}

