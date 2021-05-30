package com.example.amazon;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.amazon.categories.clothing.men.Male_dress1;
import com.example.amazon.categories.clothing.women.Dress1;
import com.example.amazon.util.AllCategoriesAdapter;
import com.example.amazon.util.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class AllCategories extends AppCompatActivity {

    ArrayList<String>NameOfProblem=new ArrayList<>();
    ArrayList<ArrayList<Integer>>icons = new ArrayList<ArrayList<Integer>>();
    ArrayList<Class>classes=new ArrayList<>();
    int disease;
    Boolean elders=false;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_all_categories2);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle extras = getIntent().getExtras();
        assert extras != null;
        disease = extras.getInt("disease");
        Utils.change((LinearLayout) findViewById(R.id.categories), disease, this);

        elders=extras.getBoolean("elder");


        AddingItems();

        RecyclerView recyclerView=findViewById(R.id.AllCategoriesRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(AllCategories.this,LinearLayoutManager.VERTICAL,false));
        AllCategoriesAdapter Adapter=new AllCategoriesAdapter(AllCategories.this,NameOfProblem,icons,classes,disease,elders);
        recyclerView.setAdapter(Adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
    void AddingItems() {
        //problem name
        NameOfProblem.add("Women");
        NameOfProblem.add("Men");


        //Images for problems
        ArrayList<Integer>a=new ArrayList<>(4);

        a.add(R.drawable.womenicon);
        a.add(R.drawable.women_p);
        a.add(R.drawable.women_d);
        a.add(R.drawable.women_t);
        icons.add(a);
        ArrayList<Integer>b=new ArrayList<>(4);

        b.add(R.drawable.menicon);
        b.add(R.drawable.men_p);
        b.add(R.drawable.men_d);
        b.add(R.drawable.men_t);

        icons.add(b);

        classes.add(Dress1.class);
        classes.add(Male_dress1.class);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_settings:
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}