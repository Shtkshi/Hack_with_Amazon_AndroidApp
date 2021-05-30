package com.example.amazon.util;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazon.R;

import java.util.ArrayList;
import java.util.List;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.TasksViewHolder> {

    private Context mCtx;
    ArrayList<ArrayList<Integer>>image;
    int disease;
    ArrayList<String>name;
    ArrayList<Class> next_activity;
    public cartAdapter(Context mCtx, ArrayList<ArrayList<Integer>> image, ArrayList<String> name, int disease, ArrayList<Class> next_activity) {
        this.mCtx = mCtx;
        this.image=image;
        this.name=name;
        this.disease=disease;
        this.next_activity=next_activity;

    }


    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.cart_recycler, parent, false);
        return new TasksViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {
        holder.image.setImageResource(image.get(position).get(disease));
        holder.ItemName.setText(name.get(position));
        holder.Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mCtx,next_activity.get(position));
                intent.putExtra("disease", disease);
                //intent.putExtra("elder",elders);
                mCtx.startActivity(intent);



            }
        });


    }


    @Override
    public int getItemCount() {
        return name.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView ItemName,Details;


        public TasksViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            image = itemView.findViewById(R.id.cart_image);
            ItemName = itemView.findViewById(R.id.cart_name);
            Details=itemView.findViewById(R.id.cart_viewdetails);



        }

        @Override
        public void onClick(View view) {
            Toast.makeText(mCtx, "CLICKED WITH TEXT", Toast.LENGTH_LONG).show();
        }

    }


}
