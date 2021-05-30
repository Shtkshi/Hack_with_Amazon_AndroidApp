package com.example.amazon.categories.clothing.men;

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
import com.example.amazon.MainActivity;
import com.example.amazon.R;
import com.example.amazon.util.Utils;

import java.util.Objects;

public class Male_dress1 extends AppCompatActivity {
    int disease;
    Boolean colorblind;
    Boolean elders=false;
    int[] imgID = {R.drawable.male1, R.drawable.male_d, R.drawable.male_p,R.drawable.male_t};
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male_dress1);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button Cart=findViewById(R.id.Cart_men);
        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Male_dress1.this, Cart.class);
                startActivity(intent);
                finish();
            }
        });


        Bundle extras = getIntent().getExtras();
        assert extras != null;
        disease = extras.getInt("disease");
        Utils.change((LinearLayout) findViewById(R.id.parentLayout), disease, this);





        elders=extras.getBoolean("elder");
        //Elder
        if(elders){
            TextView textView1=findViewById(R.id.male_details);
            textView1.setText("Product Dimensions: 15.24 x 20.32 x 2.54 cm; 162 Grams\n" +
                    "Date First Available: 9 September 2020\n" +
                    "Manufacturer: DORJE\n" +
                    "ASIN: B08HL7TZSY\n" +
                    "Item part number: DJ_IKM_M_B_L\n" +
                    "Country of Origin: India\n" +
                    "Department: Unisex\n" +
                    "Manufacturer: DORJE\n" +
                    "Item Weight: 162 g\n" +
                    "Item Dimensions LxWxH: 15.2 x 20.3 x 2.5 Centimeters");

            TextView textView2=findViewById(R.id.male_disc);
            textView2.setText("Wear The Rare, Say Hi to Dorje Trippy t shirts, Truly unique T shirts Hand drawn artwork. Simply the best psychedelic t-shirt Made with Love, High in the Himalayas by the Himalayan Wonder Lama Dorje. \n" +
                    "100% organic himalayan cotton t shirt perfectly breathable, soft & comfortable.");
        }




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