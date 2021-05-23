package com.example.amazon.categories.decor;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.amazon.R;
import com.example.amazon.util.Utils;

import java.util.Objects;

public class decor extends AppCompatActivity {

    int disease;
    Boolean colorblind;
    int[] imgID = {R.drawable.decor_item1, R.drawable.fruits_d, R.drawable.fruits_p, R.drawable.fruits_t};
    Boolean elders=false;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decor);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Fonts colorblind
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        disease = extras.getInt("disease");
        Utils.change((LinearLayout) findViewById(R.id.parentLayout_decor), disease, this);

        elders=extras.getBoolean("elder");
        //Elder
        if(elders){
            TextView textView1=findViewById(R.id.decor_disc );
            textView1.setText("foldable storage box cum sitting stool for kids Perfect for kids Colorful design is perfect to decorate the kid's room brightly and positively. Encourage children to keep their room clean by storing things in attractive stool. Use as storage box Use this as a storage box for storing children’s clothes, shoes, toys, books and other accessories in the kid's room and study area. Use as stool Use this fruit printed storage box as a sitting stool, children sofa and footrest bench for kids. Appearance of soft plush sponge offers them comfortable sitting experience. Attractive design Unique design, lovely and sweet fruit style 3D printing, cylinder shape, solid and durable.");

            TextView textView2=findViewById(R.id.decor_details);
            textView2.setText("ColourMulticolour\n" +
                    "BrandKRH store\n" +
                    "Frame MaterialWood\n" +
                    "Item ShapeOctagonal\n" +
                    "Item Dimensions LxWxH10 x 10 x 10");

            textView1.setTextSize(30);
            textView2.setTextSize(30);
        }



        //images
        disease = extras.getInt("disease");
        colorblind = extras.getBoolean("colorblind");
        if (colorblind)
            ((ImageView) findViewById(R.id.frame_decor)).setImageResource(imgID[disease]);

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