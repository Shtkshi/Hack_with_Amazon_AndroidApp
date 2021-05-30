package com.example.amazon;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
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

import com.example.amazon.bot.bot;
import com.example.amazon.categories.dailyEssentials.dailyEssentials;
import com.example.amazon.categories.decor.decor;
import com.example.amazon.categories.electronics.electronics;
import com.example.amazon.categories.groceries.groceries;
import com.example.amazon.util.Utils;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private SwitchCompat colorBlindSwitch;
    private static final int ISHIARA_TEST_RESULT_CODE = 1000;
    boolean resultEvaluated = false;
    int disease = 0;
    Menu microphone;
    Boolean ElderMode = false;
    private SwitchCompat ElderModeSwitch;
    Menu myMenu;

    int RecordAudioRequestCode = 100;
    SpeechRecognizer speechRecognizer;
    String mState = "anything";


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

    int[] vid1 = {R.raw.orig_1, R.raw.deut_1, R.raw.prot_1, R.raw.trit_1};
    int[] vid2 = {R.raw.orig_2, R.raw.deut_2, R.raw.protan_2, R.raw.trit_2};

    int[] item1 = {R.drawable.item1, R.drawable.item1_d, R.drawable.item1_p, R.drawable.item1_t};
    int[] item4 = {R.drawable.item4, R.drawable.item4_d, R.drawable.item4_p, R.drawable.item4_t};
    int[] item5 = {R.drawable.item5, R.drawable.item5_d, R.drawable.item5_p, R.drawable.item5_t};


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


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void elder() {
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                Utils.speak(tts, "Where do you want us to take you?");
            } else {
                Toast.makeText(getApplicationContext(), "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
            }
        }, "com.google.android.tts");
        tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String utteranceId) {

            }

            @Override
            public void onDone(String utteranceId) {
                tts.stop();
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                        "Speak Up");
                try {
                    startActivityForResult(intent, RecordAudioRequestCode);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(),
                            "Sorry, your device doesn't support speech input.",
                            Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(String utteranceId) {
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void special() {


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

        ElderModeSwitch = findViewById(R.id.elderSwitch);
        ElderModeSwitch.setChecked(getIntent().getBooleanExtra("Elder", false));
        ElderMode = getIntent().getBooleanExtra("Elder", false);
        if(ElderMode)      mState = "HIDE_MENU";
        else mState="anything";
        invalidateOptionsMenu(); // now onCreateOptionsMenu(...) is called again
        ElderModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Toast.makeText(MainActivity.this, "Toggled : " + isChecked, Toast.LENGTH_LONG).show();
                if (isChecked) {
                    ElderMode = true;
                    adjustFontScale(getResources().getConfiguration(), 2);
                    mState = "HIDE_MENU"; // setting state
                    //onCreateOptionsMenu(); kya hua, kuch nahi kya hoga okay done? aray mujhe thodi kuch karna tha mai to bta raha tha ki mera button gayab ho gya hai, konsa button

                } else {
                    ElderMode = false;
//                    setContentView(R.layout.activity_main);
//                    special();
//                    ColorDrawable colorDrawable
//                            = new ColorDrawable(getResources().getColor(R.color.colorPrimary, null));
//                    getSupportActionBar().setTitle(Html.fromHtml("<font color=#FFFFFF>Amazon</font>", HtmlCompat.FROM_HTML_MODE_LEGACY));
//                    getSupportActionBar().setBackgroundDrawable(colorDrawable);
                    adjustFontScale(getResources().getConfiguration(), 1);
                    mState="anything";
                    invalidateOptionsMenu(); // now onCreateOptionsMenu(...) is called again


                }
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("Elder", ElderMode);
                startActivity(intent);
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

                    adjustFontScale(getResources().getConfiguration(), 2);

                }
            } else {
                colorBlindSwitch.setChecked(false);
                adjustFontScale(getResources().getConfiguration(), 1);

//
            }
        }

        if (ElderModeSwitch.isChecked()) {
            adjustFontScale(getResources().getConfiguration(), 2);
        } else {
            adjustFontScale(getResources().getConfiguration(), 1);

        }
//&& resultCode == RESULT_OK
        if (ElderModeSwitch.isChecked() && requestCode == RecordAudioRequestCode && resultCode == RESULT_OK && data != null) {
            ArrayList<String> result = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//            Log.d("RESULT", result.get(0));
            if (result.get(0).toLowerCase().contains("kapde") || result.get(0).toLowerCase().contains("clothes") || result.get(0).toLowerCase().contains("clothing") || result.get(0).toLowerCase().contains("girl")) {
                Intent intent = new Intent(MainActivity.this, AllCategories.class);
                intent.putExtra("elder", true);
                intent.putExtra("disease", disease);

                startActivity(intent);
            } else if (result.get(0).toLowerCase().contains("groceries") || result.get(0).toLowerCase().contains("rashan") || result.get(0).toLowerCase().contains("boy") || result.get(0).toLowerCase().contains("ladke")) {
                Intent intent = new Intent(MainActivity.this, groceries.class);
                intent.putExtra("elder", true);
                intent.putExtra("disease", disease);
                startActivity(intent);
            } else if (result.get(0).toLowerCase().contains("electronics") || result.get(0).toLowerCase().contains("tv") || result.get(0).toLowerCase().contains("gadgets") || result.get(0).toLowerCase().contains("technology")) {
                Intent intent = new Intent(MainActivity.this, electronics.class);
                intent.putExtra("elder", true);
                intent.putExtra("disease", disease);

                startActivity(intent);
            } else if (result.get(0).toLowerCase().contains("decor") || result.get(0).toLowerCase().contains("sajawat ka saman") || result.get(0).toLowerCase().contains("sajane") || result.get(0).toLowerCase().contains("decorations")) {
                Intent intent = new Intent(MainActivity.this, decor.class);
                intent.putExtra("elder", true);
                intent.putExtra("disease", disease);
                startActivity(intent);
            } else if (result.get(0).toLowerCase().contains("daily needs") || result.get(0).toLowerCase().contains("roj ka saman") || result.get(0).toLowerCase().contains("rashan") || result.get(0).toLowerCase().contains("kitchen ka saman") || result.get(0).toLowerCase().contains("daily need")) {
                Intent intent = new Intent(MainActivity.this, groceries.class);
                intent.putExtra("elder", true);
                intent.putExtra("disease", disease);
                startActivity(intent);
            } else if (result.get(0).toLowerCase().contains("cart") || result.get(0).toLowerCase().contains("Take me to the cart") || result.get(0).toLowerCase().contains("cart par le jao") || result.get(0).toLowerCase().contains("buy") || result.get(0).toLowerCase().contains("done shopping")) {
                Intent intent = new Intent(MainActivity.this, Cart.class);
                intent.putExtra("elder", true);
                intent.putExtra("disease", disease);
                startActivity(intent);
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
        menu.getItem(0).setVisible(!mState.equals("anything"));

        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_1:
                voice();
                return true;
            case R.id.menu_2:
                return true;
            case R.id.menu_3:
                //bot
                Bot();
                return true;
            case R.id.menu_4:
                cart();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void Bot() {
        Intent intent = new Intent(MainActivity.this, bot.class);
        intent.putExtra("disease", disease);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void voice() {
        elder();
    }


    void cart() {
        Intent intent = new Intent(MainActivity.this, Cart.class);
        intent.putExtra("disease", disease);
        startActivity(intent);
    }

    void button_click() {

        findViewById(R.id.Clothing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean t = colorBlindSwitch.isChecked();
                Intent i = new Intent(MainActivity.this, AllCategories.class);

                i.putExtra("disease", disease);
                i.putExtra("elder", ElderMode);
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

                i.putExtra("elder", ElderMode);

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
                i.putExtra("elder", ElderMode);

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
                i.putExtra("elder", ElderMode);

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
                i.putExtra("elder", ElderMode);

                startActivity(i);


            }
        });

    }

    public void adjustFontScale(Configuration configuration, float scale) {

        configuration.fontScale = scale;
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        metrics.scaledDensity = configuration.fontScale * metrics.density;
        getBaseContext().getResources().updateConfiguration(configuration, metrics);
    }


}