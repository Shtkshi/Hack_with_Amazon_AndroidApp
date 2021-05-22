package com.example.amazon.categories.groceries;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.amazon.R;
import com.example.amazon.util.Utils;

public class groceries extends AppCompatActivity {

    int disease;
    Boolean colorblind;
    int[] imgID = {R.drawable.aata, R.drawable.fortune_d, R.drawable.fortune_p, R.drawable.fortune_t};


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groceries);

        //Fonts colorblind
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        disease = extras.getInt("disease");
        Utils.change((LinearLayout) findViewById(R.id.parentLayout_groceries), disease, this);

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