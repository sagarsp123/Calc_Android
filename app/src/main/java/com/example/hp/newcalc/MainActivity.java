package com.example.hp.newcalc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText etNumber;
    Button btnSquare, btnSquareRoot;
    TextView tvResult;
    TextToSpeech tts;

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to close this application?");
        //builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.setTitle("Exit");
        alert.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int o = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(o);

        etNumber = findViewById(R.id.etNumber);
        btnSquare = findViewById(R.id.btnSquare);
        btnSquareRoot = findViewById(R.id.btnSquareRoot);
        tvResult = findViewById(R.id.tvResult);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener(){
            public void onInit(int status){
                if (status != TextToSpeech.ERROR){
                    tts.setLanguage(Locale.ENGLISH);
                }
            }
        });

        btnSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = etNumber.getText().toString();
                if (s.length() == 0){
                    Toast.makeText(MainActivity.this, "Please enter a Number", Toast.LENGTH_SHORT).show();
                    etNumber.requestFocus();
                    return;
                }

                double n = Double.parseDouble(s);
                double r = n*n;
                Toast.makeText(MainActivity.this, "Square = " + r, Toast.LENGTH_SHORT).show();
                tvResult.setText("Square = " + r);
                tts.speak("Square = " + r, TextToSpeech.QUEUE_FLUSH, null);
            }
        });


        btnSquareRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = etNumber.getText().toString();
                if (s.length() == 0){
                    Toast.makeText(MainActivity.this, "Please enter a Number", Toast.LENGTH_SHORT).show();
                    etNumber.requestFocus();
                    return;
                }

                double n = Double.parseDouble(s);
                double r = Math.sqrt(n);
                Toast.makeText(MainActivity.this, "Square Root = " + r, Toast.LENGTH_SHORT).show();
                tvResult.setText("Square Root = " + r);
                tts.speak("Square Root = " + r, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }


}
