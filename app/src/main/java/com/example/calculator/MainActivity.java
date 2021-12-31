package com.example.calculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button simp,exit,adv,credits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simp = (Button)findViewById(R.id.simple);
        exit = (Button)findViewById(R.id.exit);
        credits = (Button)findViewById(R.id.credits);
        adv = (Button)findViewById(R.id.advanced);


        credits.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){openCredits();}
        });
        simp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSimple();
            }
        });
        adv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openAdvanced();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.app_name);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setMessage("Do you want to exit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }

    private void openSimple(){
        Intent intent= new Intent(this, simple.class);
        startActivity(intent);
    }
    private void openCredits(){
        Intent intent = new Intent(this,credits.class);
        startActivity(intent);
    }
    private void openAdvanced(){
        Intent intent = new Intent(this, advanced.class);
        startActivity(intent);
    }


}