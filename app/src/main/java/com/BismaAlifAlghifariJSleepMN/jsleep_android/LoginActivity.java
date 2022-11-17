package com.BismaAlifAlghifariJSleepMN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.BismaAlifAlghifariJSleepMN.jsleep_android.model.Room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView register = findViewById(R.id.textView4);
        Button mainActivity = findViewById(R.id.button);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Intent move = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(move);
            }
        });

        mainActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Intent move = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(move);
            }
        });


    }

}