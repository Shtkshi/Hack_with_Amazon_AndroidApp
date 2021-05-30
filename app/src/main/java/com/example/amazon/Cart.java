package com.example.amazon;

import android.os.Build;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazon.categories.clothing.men.Male_dress1;
import com.example.amazon.categories.clothing.women.Dress1;
import com.example.amazon.categories.dailyEssentials.dailyEssentials;
import com.example.amazon.categories.decor.decor;
import com.example.amazon.categories.electronics.electronics;
import com.example.amazon.categories.groceries.groceries;
import com.example.amazon.util.Utils;
import com.example.amazon.util.cartAdapter;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    ArrayList<String> name = new ArrayList<>();
    ArrayList<ArrayList<Integer>> image = new ArrayList<ArrayList<Integer>>();
    RecyclerView recyclerView;
    cartAdapter cartAdapter;
    int disease;
    ArrayList<Class>next_activity=new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Bundle extras = getIntent().getExtras();
        assert extras != null;
        disease = extras.getInt("disease");
        Utils.change((LinearLayout) findViewById(R.id.categories), disease, this);


        add_Data();




        recyclerView = (RecyclerView) findViewById(R.id.cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(Cart.this, LinearLayoutManager.VERTICAL, false));
        cartAdapter = new cartAdapter(Cart.this, image, name, disease,next_activity);
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    void add_Data() {
        name.add("Tshirt for men");
        name.add("Tshirt for women");
        name.add("All Out");
        name.add("decor");
        name.add("TV");
        name.add("Atta");

        next_activity.add(Male_dress1.class);
        next_activity.add(Dress1.class);
        next_activity.add(dailyEssentials.class);
        next_activity.add(decor.class);
        next_activity.add(electronics.class);
        next_activity.add(groceries.class);


        ArrayList<Integer> images = new ArrayList<>(4);
        images.add(R.drawable.male1);
        images.add(R.drawable.male_d);
        images.add(R.drawable.male_p);
        images.add(R.drawable.male_t);

        image.add(images);


        ArrayList<Integer> a = new ArrayList<>(4);

        a.add(R.drawable.femaletwo);
        a.add(R.drawable.tshirt_d);
        a.add(R.drawable.tshirt_p);
        a.add(R.drawable.tshirt_t);

        image.add(a);
        ArrayList<Integer> b = new ArrayList<>(4);

        b.add(R.drawable.all_out);
        b.add(R.drawable.gold_d);
        b.add(R.drawable.gold_p);
        b.add(R.drawable.gold_t);

        image.add(b);

        ArrayList<Integer> c = new ArrayList<>(4);

        c.add(R.drawable.decor_item1);
        c.add(R.drawable.fruits_d);
        c.add(R.drawable.fruits_p);
        c.add(R.drawable.fruits_t);

        image.add(c);


        ArrayList<Integer> d = new ArrayList<>(4);

        d.add(R.drawable.tv);
        d.add(R.drawable.bal_d);
        d.add(R.drawable.bal_p);
        d.add(R.drawable.bal_t);

        image.add(d);

        ArrayList<Integer> e = new ArrayList<>(4);

        e.add(R.drawable.aata);
        e.add(R.drawable.fortune_d);
        e.add(R.drawable.fortune_p);
        e.add(R.drawable.fortune_t);

        image.add(images);




    }
}