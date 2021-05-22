package com.example.amazon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.text.HtmlCompat;

import com.example.amazon.categories.dailyEssentials.dailyEssentials;
import com.example.amazon.categories.decor.decor;
import com.example.amazon.categories.electronics.electronics;
import com.example.amazon.categories.groceries.groceries;
import com.example.amazon.util.Utils;

public class MainActivity extends AppCompatActivity {

    private SwitchCompat colorBlindSwitch;
    private static final int ISHIARA_TEST_RESULT_CODE = 1000;
    boolean resultEvaluated = false;
    int disease=0;
    Menu microphone;
    Boolean ElderMode=false;
    private SwitchCompat ElderModeSwitch;
    Menu myMenu;


    int[] clothing = {R.drawable.clothing, R.drawable.clothing_d, R.drawable.clothing_p, R.drawable.clothing_t};
    int[] groceries = {R.drawable.groceries, R.drawable.groceries_d, R.drawable.groceries_p, R.drawable.groceries_t};
    int[] electronics = {R.drawable.electronics, R.drawable.electronic_d, R.drawable.electronic_p, R.drawable.electronic_t};
    int[] daily_needs = {R.drawable.daily, R.drawable.daily_d, R.drawable.daily_p, R.drawable.daily_t};
    int[] decor = {R.drawable.decor, R.drawable.home_d, R.drawable.home_p, R.drawable.home_t};



    int[] e = {R.drawable.e, R.drawable.e_d, R.drawable.e_p, R.drawable.e_t};
    int[] h = {R.drawable.h, R.drawable.h_d, R.drawable.h_p, R.drawable.h_t};
    int[] g = {R.drawable.g, R.drawable.g_d, R.drawable.g_p, R.drawable.g_t};
    int[] c = {R.drawable.c, R.drawable.c_d, R.drawable.c_p, R.drawable.c_t};
    int[] b = {R.drawable.b, R.drawable.b_d, R.drawable.b_p, R.drawable.b_t};
    int[] f = {R.drawable.f, R.drawable.f_d, R.drawable.f_p, R.drawable.f_t};

    int[] vid1={R.raw.orig_1,R.raw.deut_1,R.raw.prot_1,R.raw.trit_1};
    int[] vid2={R.raw.orig_2,R.raw.deut_2,R.raw.protan_2,R.raw.trit_2};

    int[] item1={R.drawable.item1, R.drawable.item1_d, R.drawable.item1_p, R.drawable.item1_t};
    int[] item4={R.drawable.item4, R.drawable.item4_d, R.drawable.item4_p, R.drawable.item4_t};
    int[] item5={R.drawable.item5, R.drawable.item5_d, R.drawable.item5_p, R.drawable.item5_t};



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        special();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void special(){

        Utils.change((RelativeLayout) findViewById(R.id.parentLayout), disease, this);


        VideoView videoView = findViewById(R.id.video);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +
                R.raw.orig_1));
        videoView.start();
        VideoView videoView2 = findViewById(R.id.video2);
        MediaController mediaController2 = new MediaController(this);
        mediaController2.setAnchorView(videoView2);
        videoView2.setMediaController(mediaController2);
        videoView2.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +
                R.raw.orig_2));
        videoView2.start();


        button_click();

        ElderModeSwitch= findViewById(R.id.elderSwitch);
        ElderModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ElderMode=true;


                } else {
                    ElderMode = false;
                    setContentView(R.layout.activity_main);
                    special();
                    ColorDrawable colorDrawable
                            = new ColorDrawable(getResources().getColor(R.color.colorPrimary, null));
                    getSupportActionBar().setTitle(Html.fromHtml("<font color=#FFFFFF>Amazon</font>", HtmlCompat.FROM_HTML_MODE_LEGACY));
                    getSupportActionBar().setBackgroundDrawable(colorDrawable);


                }
            }
        });



        colorBlindSwitch = findViewById(R.id.colorBlindSwitch);
        colorBlindSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    startActivityForResult(new Intent(MainActivity.this, Ishihara.class), ISHIARA_TEST_RESULT_CODE);
//
                } else {
                    disease = 0;
                    setContentView(R.layout.activity_main);
                    special();
                    ColorDrawable colorDrawable
                            = new ColorDrawable(getResources().getColor(R.color.colorPrimary, null));
                    getSupportActionBar().setTitle(Html.fromHtml("<font color=#FFFFFF>Amazon</font>", HtmlCompat.FROM_HTML_MODE_LEGACY));
                    getSupportActionBar().setBackgroundDrawable(colorDrawable);

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (tts != null)
            tts.stop();
        super.onBackPressed();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ISHIARA_TEST_RESULT_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    int score = data.getIntExtra("result", 0);
                    disease = data.getIntExtra("disease", 0);
                    Utils.change((RelativeLayout) findViewById(R.id.parentLayout), disease, this);
                    Toast.makeText(MainActivity.this, "Result Received" + score, Toast.LENGTH_LONG).show();
                    resultEvaluated = true;


                    ((ImageButton) findViewById(R.id.Clothing)).setImageResource(clothing[disease]);
                    ((ImageButton) findViewById(R.id.Groceries)).setImageResource(groceries[disease]);
                    ((ImageButton) findViewById(R.id.electronics)).setImageResource(electronics[disease]);
                    ((ImageButton) findViewById(R.id.DailyNeeds)).setImageResource(daily_needs[disease]);
                    ((ImageButton) findViewById(R.id.Decorations)).setImageResource(decor[disease]);


                    ((ImageButton) findViewById(R.id.e)).setImageResource(e[disease]);
                    ((ImageButton) findViewById(R.id.h)).setImageResource(h[disease]);
                    ((ImageButton) findViewById(R.id.g)).setImageResource(g[disease]);
                    ((ImageButton) findViewById(R.id.c)).setImageResource(c[disease]);
                    ((ImageButton) findViewById(R.id.b)).setImageResource(b[disease]);
                    ((ImageButton) findViewById(R.id.f)).setImageResource(f[disease]);
                    ((ImageButton) findViewById(R.id.dup_b)).setImageResource(b[disease]);
                    ((ImageButton) findViewById(R.id.dup_g)).setImageResource(g[disease]);
                    ((ImageButton) findViewById(R.id.item1)).setImageResource(item1[disease]);
                    ((ImageButton) findViewById(R.id.item4)).setImageResource(item4[disease]);
                    ((ImageButton) findViewById(R.id.item5)).setImageResource(item5[disease]);
                    ((VideoView) findViewById(R.id.video2)).setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +
                            vid2[disease]));

                    ((VideoView) findViewById(R.id.video)).setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +
                            vid1[disease]));
                }
            } else {
                colorBlindSwitch.setChecked(false);
//                ElderModeSwitch.setChecked(false);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    TextToSpeech tts;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();



        inflater.inflate(R.menu.toolbar_options, menu);
        // show the button when some condition is true
        MenuItem item = menu.findItem(R.id.menu_1);
        if (item != null) {
            item.setVisible(true);
        }

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_1:
                
                return true;
            case R.id.menu_2:
                return true;
            case R.id.menu_3:
                return true;
            case R.id.menu_4:
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void button_click(){

        findViewById(R.id.Clothing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean t = colorBlindSwitch.isChecked();
                Intent i = new Intent(MainActivity.this, AllCategories.class);

                i.putExtra("disease", disease);
                i.putExtra("colorblind", t);
                startActivity(i);


            }
        });
        findViewById(R.id.Groceries).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean t = colorBlindSwitch.isChecked();
                Intent i = new Intent(MainActivity.this, groceries.class);

                i.putExtra("disease", disease);
                i.putExtra("colorblind", t);
                startActivity(i);


            }
        });

        findViewById(R.id.DailyNeeds).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean t = colorBlindSwitch.isChecked();
                Intent i = new Intent(MainActivity.this, dailyEssentials.class);

                i.putExtra("disease", disease);
                i.putExtra("colorblind", t);
                startActivity(i);


            }
        });

        findViewById(R.id.Decorations).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean t = colorBlindSwitch.isChecked();
                Intent i = new Intent(MainActivity.this, decor.class);

                i.putExtra("disease", disease);
                i.putExtra("colorblind", t);
                startActivity(i);


            }
        });


        findViewById(R.id.electronics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean t = colorBlindSwitch.isChecked();
                Intent i = new Intent(MainActivity.this, electronics.class);

                i.putExtra("disease", disease);
                i.putExtra("colorblind", t);
                startActivity(i);


            }
        });

    }



}