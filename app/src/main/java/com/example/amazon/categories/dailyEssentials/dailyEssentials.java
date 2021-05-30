package com.example.amazon.categories.dailyEssentials;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.amazon.Cart;
import com.example.amazon.R;
import com.example.amazon.categories.clothing.men.Male_dress1;
import com.example.amazon.util.Utils;

import org.w3c.dom.Text;

import java.util.Objects;

public class dailyEssentials extends AppCompatActivity {
    int disease;
    Boolean colorblind;
    Boolean elders=false;
    int[] imgID = {R.drawable.all_out, R.drawable.gold_d, R.drawable.gold_p,R.drawable.gold_t};

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_essentials);

        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button Cart=findViewById(R.id.Cart_daily);
        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(dailyEssentials.this, com.example.amazon.Cart.class);
                startActivity(intent);
                finish();
            }
        });


        //Fonts colorblind
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        disease = extras.getInt("disease");
        Utils.change((LinearLayout) findViewById(R.id.parentLayout_dailyEssentials), disease, this);

        elders=extras.getBoolean("elder");
        //Elder
        if(elders){
            TextView textView1=findViewById(R.id.DailyNeeds_details);
            textView1.setText("Item Weight: 108 g\n" +
                    "Item Dimensions LxWxH: 20.9 x 5.7 x 8.8 Centimeters\n" +
                    "Net Quantity: 1 U\n" +
                    "Included Components: Good Knight Flash LMD Combi (Machine + 3 Refills)");

            TextView textView2=findViewById(R.id.DailyNeeds_disc);
            textView2.setText("INDIA’S MOST POWERFUL LIQUID VAPOURISER powered by Gold Flash Technology\n" +
                    "Flash mode releases visible vapours for 30 minutes every 4 hours\n" +
                    "Dual mode: Switch between Normal and Flash Mode, depending on the number of mosquitoes.");

        }


        //images
        disease=extras.getInt("disease");
        colorblind=extras.getBoolean("colorblind");
        if(colorblind)
            ((ImageView) findViewById(R.id.frame_daily)).setImageResource(imgID[disease]);

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