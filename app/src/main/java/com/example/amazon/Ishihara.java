package com.example.amazon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.example.amazon.util.Utils;
import com.google.android.material.textfield.TextInputEditText;

public class Ishihara extends AppCompatActivity {
    Button submitIshihara;
    boolean eval = false;
    int count = 0;
    //, R.drawable.seven, R.drawable.twelve, R.drawable.eleven, R.drawable.thirteen, R.drawable.six, R.drawable.eight, R.drawable.forteen, R.drawable.eighteen, R.drawable.fifteen, R.drawable.five, R.drawable.four, R.drawable.nineteen, R.drawable.seventeen, R.drawable.sixteen, R.drawable.three, R.drawable.twenty, R.drawable.twentyone, R.drawable.two
    int[] drawables = {R.drawable.one, R.drawable.ten, R.drawable.nine};
    String[] solution = {"12", "2", "74"};
    //, "3", "97", "6", "45", "5", "15", "5", "26", "7", "57", "29", "42", "73", "16", "6", "35", "96", "8"
    int True = 0, ones = 0, tens = 0, False = 0;
    boolean[] ans = new boolean[21]; // sixe aaega andar, ab karle isse, chalega? yahi chahiye tha?
    int disease = Utils.Disease.None.getNumVal();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ishihara);
        ((ImageView) findViewById(R.id.mainFrame)).setImageDrawable(ContextCompat.getDrawable(Ishihara.this, drawables[0]));
        findViewById(R.id.colorBlindInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.frame2).setVisibility(View.GONE);
                findViewById(R.id.frame1).setVisibility(View.VISIBLE);
            }
        });
        submitIshihara = findViewById(R.id.submitIshiara);
        submitIshihara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (submitIshihara.getText().toString().toLowerCase().contains("skip")) {
                    findViewById(R.id.frame2).setVisibility(View.GONE);
                    findViewById(R.id.frame1).setVisibility(View.VISIBLE);
                    submitIshihara.setText("Next >");
                    return;
                }
                if (submitIshihara.getText().toString().toLowerCase().contains("return")) {
                    finish();
                    return;
                }

                String editAns = ((TextInputEditText) findViewById(R.id.answer)).getText().toString();
                if (editAns.isEmpty()) {
                    Toast.makeText(Ishihara.this, "Please enter some text.", Toast.LENGTH_LONG).show();
                    return;
                }
                editAns = editAns.trim();
                eval = editAns.equals(solution[count]);
                ans[count] = eval;
                if (eval) {
                    Toast.makeText(Ishihara.this, "Correct answer", Toast.LENGTH_SHORT).show();
                    True++;
                } else {
                    if ((editAns.charAt(editAns.length() - 1)) == (solution[count].charAt(solution[count].length() - 1))) {
                        ones++;
                    }
                    if ((editAns.charAt(0)) == (solution[count].charAt(0))) {
                        tens++;
                    }
                    False++;
                    Toast.makeText(Ishihara.this, "Wrong answer", Toast.LENGTH_SHORT).show();
                }
                if (count == drawables.length - 1) {

                    findViewById(R.id.frame1).setVisibility(View.GONE);
                    findViewById(R.id.frame2).setVisibility(View.VISIBLE);
                    disease = 0;

                    if(False==1){
                        disease = Utils.Disease.DName.getNumVal();
                        ((AppCompatTextView) findViewById(R.id.colorBlindInfo)).setText("You have Deuteranopia type of colour blindess(red-green)");
                    }else if(False==2){
                        disease = Utils.Disease.PName.getNumVal();
                        ((AppCompatTextView) findViewById(R.id.colorBlindInfo)).setText("You have Protanopia type of colour blindess(red-green");
                    }else if(False==3){
                        ((AppCompatTextView) findViewById(R.id.colorBlindInfo)).setText("You have Tritanopia type of colourblindess (blue and green, purple and red, and yellow and pink)");
                        disease = Utils.Disease.TName.getNumVal();
                    }else if(False==0){
                        ((AppCompatTextView) findViewById(R.id.colorBlindInfo)).setText("You dont have color blindness");
                        disease = 0;
                    }
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("result", True);
                    resultIntent.putExtra("disease", disease);
                    setResult(Activity.RESULT_OK, resultIntent); // done? ek baar check kar lu , okay baki sab git mai dalna hai kese dalte hai? dikha duga, check karloya?
                    submitIshihara.setText("Return to the main menu.");
                    return;
                }
                if (count == drawables.length - 2) {
                    submitIshihara.setText("Submit");
                }
                count++;
                ((TextInputEditText) findViewById(R.id.answer)).setText("");
                ((ImageView) findViewById(R.id.mainFrame)).setImageDrawable(ContextCompat.getDrawable(Ishihara.this, drawables[count]));
                eval = false;
            }
        });
    }
}
