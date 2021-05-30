package com.example.amazon.util;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;

import com.example.amazon.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static final String serverUrl = "http://192.168.1.240:5000";
    static ArrayList<Map<String, String>> colour = new ArrayList<Map<String, String>>() {
        {
            add(new HashMap<String, String>());
            add(new HashMap<String, String>() {
                {
                    put("#FFFFFF", "#FFFFFF");
                    put("#FFC0CB", "#FFC0CB");
                    put("#000000", "#000000");
                    put("#F7913C", "#F7913C");
                    put("#2162F9", "#2162F9");
                    put("#132436", "#132436");
                    put("#FF0000", "#FF0000");
                    put("#F98204", "#FC7E9E");
                    put("#EB3E04", "#FB30B2");
                    put("#F30302", "#FE00B2");
                    put("#B70100", "#B80080");
                    put("#561239", "#560E50");
                    put("#6E007F", "#6E007F");
                    put("#1F034D", "#1F034D");
                    put("#001185", "#001185");
                    put("#01416F", "#01416F");
                    put("#007612", "#007612");
                    put("#3EB40A", "#3EB40A");
                    put("#FDF701", "#FDF701");
                }
            });
            add(new HashMap<String, String>() {
                {
                    put("#FFFFFF", "#FFFFFF");
                    put("#FFC0CB", "#FFC0CB");
                    put("#000000", "#000000");
                    put("#F7913C", "#F7913C");
                    put("#2162F9", "#2162F9");
                    put("#132436", "#132436");
                    put("#FF0000", "#FF0000");
                    put("#F98204", "#FDBF40");
                    put("#EB3E04", "#FC9B79");
                    put("#F30302", "#FB8199");
                    put("#B70100", "#B85D71");
                    put("#561239", "#573768");
                    put("#6E007F", "#6F3768");
                    put("#1F034D", "#211063");
                    put("#001185", "#011E49");
                    put("#01416F", "#004800");
                    put("#007612", "#3F7A00");
                    put("#3EB40A", "#3EB40A");
                    put("#FDF701", "#FDF701");
                }
            });
            add(new HashMap<String, String>() {
                {
                    put("#FFFFFF", "#FFFFFF");
                    put("#FFC0CB", "#FFC0CB");
                    put("#000000", "#000000");
                    put("#F7913C", "#F7913C");
                    put("#2162F9", "#2162F9");
                    put("#132436", "#132436");
                    put("#FF0000", "#FF0000");
                    put("#F98204", "#FC6DFF");
                    put("#EB3E04", "#FB1AFF");
                    put("#F30302", "#FE00FF");
                    put("#B70100", "#B900FF");
                    put("#561239", "#580BFF");
                    put("#6E007F", "#6F00FF");
                    put("#1F034D", "#FFFFFE");
                    put("#001185", "#091742");
                    put("#01416F", "#004800");
                    put("#007612", "#008600");
                    put("#3EB40A", "#3FC900");
                    put("#FDF701", "#FFF808");
                }
            });
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void speak(TextToSpeech tts, String message) {
        tts.speak(message, TextToSpeech.QUEUE_FLUSH, null, "InitText");
        Log.i("TTS", "Initialization success.");
    }
    public enum Disease {
        None(0),
        DName(1),
        PName(2),
        TName(3);

        private int numVal;

        Disease(int numVal) {
            this.numVal = numVal;
        }

        public int getNumVal() {
            return numVal;
        }
    }


    //LocaleConfigurationUtil.class
    public static Context adjustFontSize(Context context){
        Configuration configuration = context.getResources().getConfiguration();
        // This will apply to all text like -> Your given text size * fontScale
        configuration.fontScale = 1.0f;

        return context.createConfigurationContext(configuration);
    }




    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void change(ViewGroup layout, int disease, AppCompatActivity activity) {
        if (disease == 0 || layout == null) {
            return;
        }
        changeLayout(layout, disease);
        String hexColorTitle = String.format("#%06X", (0xFFFFFF & activity.getTitleColor()));

        activity.getSupportActionBar().setTitle(Html.fromHtml("<font color=" + colour.get(disease).get(hexColorTitle) + ">Amazon</font>", HtmlCompat.FROM_HTML_MODE_LEGACY));
        int hexColorBGint = Color.parseColor("#" + Integer.toHexString(ContextCompat.getColor(activity, R.color.colorPrimary)));
        String hexColorBG = String.format("#%06X", (0xFFFFFF & hexColorBGint));
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor(colour.get(disease).get(hexColorBG)));

        // Set BackgroundDrawable

        activity.getSupportActionBar().setBackgroundDrawable(colorDrawable);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private static void changeLayout(ViewGroup layout, int disease) {
        if (disease == 0 || layout == null) {
            return;
        }
        int hexColorBGint = 0;
        if (layout.getBackground() instanceof ColorDrawable) {
            hexColorBGint = ((ColorDrawable) layout.getBackground()).getColor();
            String hexColorBG = String.format("#%06X", (0xFFFFFF & hexColorBGint));
            Log.d("color1", hexColorBG);
            int c = Color.parseColor(colour.get(disease).get(hexColorBG));
            Log.d("color22", String.valueOf(c));
            layout.setBackgroundColor(c);
        } else if (layout.getBackground() instanceof GradientDrawable) {
            hexColorBGint = ((GradientDrawable) layout.getBackground()).getColor().getDefaultColor();
            String hexColorBG = String.format("#%06X", (0xFFFFFF & hexColorBGint));
            Log.d("color1", hexColorBG);
            int c = Color.parseColor(colour.get(disease).get(hexColorBG));
            Log.d("color22", String.valueOf(c));
            layout.setBackgroundColor(c);
        }

        int len = layout.getChildCount();
        for (int i = 0; i < len; i++) {
            if (layout.getChildAt(i) instanceof ViewGroup) {
                //changeLayout(layout, disease);
            } else {
                if (layout.getChildAt(i) instanceof TextView) {
                    String hexColor = String.format("#%06X", (0xFFFFFF & ((TextView) layout.getChildAt(i)).getCurrentTextColor()));
                    ((TextView) layout.getChildAt(i)).setTextColor(Color.parseColor(colour.get(disease).get(hexColor)));
                }
                if (layout.getChildAt(i).getBackground() instanceof ColorDrawable) {
                    hexColorBGint = ((ColorDrawable) layout.getChildAt(i).getBackground()).getColor();
                    String hexColorBG = String.format("#%06X", (0xFFFFFF & hexColorBGint));
                    layout.getChildAt(i).setBackgroundColor(Color.parseColor(colour.get(disease).get(hexColorBG)));
                } else if (layout.getChildAt(i).getBackground() instanceof GradientDrawable) {
                    hexColorBGint = ((GradientDrawable) layout.getChildAt(i).getBackground()).getColor().getDefaultColor();
                    String hexColorBG = String.format("#%06X", (0xFFFFFF & hexColorBGint));
                    layout.getChildAt(i).setBackgroundColor(Color.parseColor(colour.get(disease).get(hexColorBG)));
                }

            }
        }

    }
}
