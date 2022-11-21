package com.BismaAlifAlghifariJSleepMN.jsleep_android;

import static android.widget.Toast.*;

import androidx.appcompat.app.AppCompatActivity;
import com.BismaAlifAlghifariJSleepMN.jsleep_android.request.*;
import com.BismaAlifAlghifariJSleepMN.jsleep_android.model.*;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLOutput;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    BaseApiService mApiService;
    EditText username,password;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        TextView register = findViewById(R.id.registerButton);
        username = findViewById(R.id.emailEdit);
        password = findViewById(R.id.passwordEdit);

        Button mainActivity = findViewById(R.id.login);
        mainActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Intent move = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(move);
                Account account = requestAccount();
            }
        });


        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Intent move = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(move);
            }
        });




    }

    protected Account requestAccount(){
        mApiService.getAccount(0).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()){
                    Account account;
                    account = response.body();
                    System.out.println(account.toString());
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(mContext, "no Account id = 0", LENGTH_SHORT).show();
            }
        });
        return null;
    }

}