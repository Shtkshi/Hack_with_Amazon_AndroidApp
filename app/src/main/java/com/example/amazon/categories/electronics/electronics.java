package com.example.amazon.categories.electronics;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.amazon.Cart;
import com.example.amazon.R;
import com.example.amazon.categories.clothing.men.Male_dress1;
import com.example.amazon.util.Utils;

import java.util.Objects;

public class electronics extends AppCompatActivity {

    int disease;
    Boolean colorblind;
    Boolean elders=false;
    int[] imgID = {R.drawable.tv, R.drawable.bal_d, R.drawable.bal_p, R.drawable.bal_t};


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronics);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button Cart=findViewById(R.id.Cart_electronics);
        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(electronics.this, com.example.amazon.Cart.class);
                startActivity(intent);
                finish();
            }
        });


        //Fonts colorblind
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        disease = extras.getInt("disease");
        Utils.change((LinearLayout) findViewById(R.id.parentLayout_electronics), disease, this);


        elders=extras.getBoolean("elder");
        //Elder
        if(elders){

            TextView textView1=(TextView)findViewById(R.id.electronics_details);
            textView1.setText("Mounting TypeWall Mount\n" +
                    "BrandSamsung\n" +
                    "Resolution4K\n" +
                    "Connector TypeUSB, Built-in Wi-fi\n" +
                    "Display TechnologyLED\n" +
                    "Supported Internet Services like Netflix\n" +
                    "ColourBlack\n" +
                    "Screen Size55 Inches\n" +
                    " Weight14200 Grams");

            TextView textView=(TextView)findViewById(R.id.electronics_text);
            textView.setText("Crystal Processor 4k\n" +
                    "Powerful 4K upscaling ensures you get up to 4K resolution for the content you love. \n You'll also experience more lifelike color expressions due to its sophisticated color mapping technology");
        }


        //images
        disease = extras.getInt("disease");
        colorblind = extras.getBoolean("colorblind");
        if (colorblind)
            ((ImageView) findViewById(R.id.frame_electronics)).setImageResource(imgID[disease]);

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
