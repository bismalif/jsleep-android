package com.BismaAlifAlghifariJSleepMN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.BismaAlifAlghifariJSleepMN.jsleep_android.model.Account;
import com.BismaAlifAlghifariJSleepMN.jsleep_android.request.BaseApiService;
import com.BismaAlifAlghifariJSleepMN.jsleep_android.request.UtilsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    BaseApiService mApiService;
    EditText username, email,  password;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        username = findViewById(R.id.registerName);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);
        Button mainActivity = findViewById(R.id.registerButton2);

        mainActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Account account = requestLogin();
            }
        });
    }

    protected Account requestLogin(){
        mApiService.login(email.getText().toString(), password.getText().toString()).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()){

                    MainActivity.cookies = response.body();

                    Intent go = new Intent(RegisterActivity.this,
                            LoginActivity.class);

                    startActivity(go);
                    Toast.makeText(mContext, "Register Successfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t){
                System.out.println(t.toString());

                Toast.makeText(mContext, "Invalid input for email or password",
                        Toast.LENGTH_SHORT).show();
            }
        });

        return null;
    }




}