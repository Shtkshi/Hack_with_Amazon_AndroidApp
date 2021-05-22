package com.example.amazon.categories.clothing.men;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.amazon.MainActivity;
import com.example.amazon.R;
import com.example.amazon.util.Utils;

import java.util.Objects;

public class Male_dress1 extends AppCompatActivity {
    int disease;
    Boolean colorblind;
    int[] imgID = {R.drawable.male1, R.drawable.male_d, R.drawable.male_p,R.drawable.male_t};
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male_dress1);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        assert extras != null;
        disease = extras.getInt("disease");
        Utils.change((LinearLayout) findViewById(R.id.parentLayout), disease, this);

        findViewById(R.id._men).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Male_dress1.this, MainActivity.class);
                intent.putExtra("productId", imgID[disease]);
                intent.putExtra("disease",disease);
                startActivity(intent);
            }
        });


        disease=extras.getInt("disease");
        colorblind=extras.getBoolean("colorblind");
        if(colorblind)
            ((ImageView) findViewById(R.id.male_dress1)).setImageResource(imgID[disease]);
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