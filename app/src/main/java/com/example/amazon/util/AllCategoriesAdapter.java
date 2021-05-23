package com.example.amazon.util;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazon.R;

import java.util.ArrayList;

public class AllCategoriesAdapter extends RecyclerView.Adapter<AllCategoriesAdapter.TasksViewHolder> {

    ArrayList<String> Name;
    ArrayList<ArrayList<Integer>>image;
    ArrayList<Class>classes;
    Context mCtx;
    Boolean elders;
    int disease;
    public AllCategoriesAdapter(Context mCtx, ArrayList<String> Name, ArrayList<ArrayList<Integer>> image, ArrayList<Class> classes,int disease, Boolean elders) {
        this.mCtx = mCtx;
        this.Name = Name;
        this.image = image;
        this.classes = classes;
        this.disease=disease;
        this.elders=elders;
    }


    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.allcategoriesrecyclerview, parent, false);
        return new TasksViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {
        holder.Image_Category.setImageResource(image.get(position).get(disease));
        holder.All.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mCtx,classes.get(position));
                intent.putExtra("disease", disease);
                intent.putExtra("elder",elders);
                mCtx.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return 2;
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView Name_Category;
        ImageView Image_Category;
        LinearLayout All;


        public TasksViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            Image_Category=itemView.findViewById(R.id.image_category);
            All=itemView.findViewById(R.id.CategoryAllLinearView);

        }

        @Override
        public void onClick(View view) {
        }

    }


}