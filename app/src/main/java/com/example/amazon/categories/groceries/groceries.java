package com.example.amazon.categories.groceries;

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

public class groceries extends AppCompatActivity {

    int disease;
    Boolean colorblind;
    int[] imgID = {R.drawable.aata, R.drawable.fortune_d, R.drawable.fortune_p, R.drawable.fortune_t};
    Boolean elders=false;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groceries);


        Button Cart=findViewById(R.id.Cart_groceries);
        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(groceries.this, com.example.amazon.Cart.class);
                intent.putExtra("disease",disease);
                startActivity(intent);
                finish();
            }
        });


        //Fonts colorblind
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        disease = extras.getInt("disease");
        Utils.change((LinearLayout) findViewById(R.id.parentLayout_groceries), disease, this);


        elders=extras.getBoolean("elder");
        //Elder
        if(elders){
            TextView textView1=findViewById(R.id.groceries_details);
            textView1.setText("Net Content Volume1000 Millilitres\n" +
                    "BrandFORTUNE\n" +
                    "Plant or Animal Product TypeSunflower\n" +
                    "Item Volume1 Litres\n" +
                    "Package Weight0.91 Kilograms");

            TextView textView2=findViewById(R.id.groceries_disc);
            textView2.setText("Light and fluffy rotis are guaranteed, with the asli phulke wala atta! Made with the finest wheat crops, Fortune Chakki Fresh Atta ensures with every meal your loved ones say, ek phulka aur. Can also be used to make a variety of other sweets and savouries.Unit 10 kg Shelf Life 3 months Country of Origin IndiaDescription Handpicked from India's finest wheat fields, Fortune Chakki Fresh Atta is made with 100% atta and 0% maida which complements your Ghar ka Khana perfectly. Fortune Chakki Fresh Atta");

        }


        //images
        disease = extras.getInt("disease");
        colorblind = extras.getBoolean("colorblind");
        if (colorblind)
            ((ImageView) findViewById(R.id.frame_groceries)).setImageResource(imgID[disease]);

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
