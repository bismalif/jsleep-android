package com.BismaAlifAlghifariJSleepMN.jsleep_android;

import static com.BismaAlifAlghifariJSleepMN.jsleep_android.MainActivity.cookies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.BismaAlifAlghifariJSleepMN.jsleep_android.model.Account;
import com.BismaAlifAlghifariJSleepMN.jsleep_android.model.Renter;
import com.BismaAlifAlghifariJSleepMN.jsleep_android.request.BaseApiService;
import com.BismaAlifAlghifariJSleepMN.jsleep_android.request.UtilsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AboutMeActivity extends AppCompatActivity {
    TextView name, email, balance;
    EditText registerRentName,registerRentAddress, registerRentPhone;
    EditText renterName, renterAddress, renterPhone;
    Button buttonRegisterCancel, buttonCreateRenter, buttonRegisterRenter;
    CardView cardRenterDetails, cardRegisterRenter, cardAccount;
    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        //Acc Details
        name = findViewById(R.id.detailName);
        email = findViewById(R.id.detailEmail);
        balance = findViewById(R.id.detailBalance);

        name.setText(cookies.name);
        email.setText(cookies.email);
        balance.setText(String.valueOf(cookies.balance));

        //Button Reg
        buttonCreateRenter = findViewById(R.id.registerRenter);
        buttonRegisterRenter = findViewById(R.id.newRegisterRenter);
        buttonRegisterCancel = findViewById(R.id.registerRenterCancel);

        //Register Renter
        registerRentName = findViewById(R.id.registerRenterName);
        registerRentAddress = findViewById(R.id.registerRenterAddress);
        registerRentPhone = findViewById(R.id.registerRenterPhoneNumber);

        //Renter Details
        renterName = findViewById(R.id.detailRenterName);
        renterAddress = findViewById(R.id.detailRenterAddress);
        renterPhone = findViewById(R.id.detailRenterPhoneNumber);

        //Card
        cardAccount = findViewById(R.id.cardAccount);
        cardRegisterRenter = findViewById(R.id.cardRegisterRenter);
        cardRenterDetails = findViewById(R.id.cardRenterDetails);

        buttonCreateRenter.setVisibility(View.GONE);
        cardRegisterRenter.setVisibility(View.GONE);
        cardRenterDetails.setVisibility(View.GONE);

        if (cookies.renter ==  null){
            buttonCreateRenter.setVisibility(View.VISIBLE);
        } else {
            cardRenterDetails.setVisibility(View.VISIBLE);
            renterName.setText(MainActivity.cookies.renter.username);
            renterAddress.setText(MainActivity.cookies.renter.address);
            renterPhone.setText(cookies.renter.phoneNumber);
        }

        buttonCreateRenter.setOnClickListener(view -> {
            buttonCreateRenter.setVisibility(View.GONE);
            cardRenterDetails.setVisibility(View.GONE);
            cardRegisterRenter.setVisibility(View.VISIBLE);
        });

        buttonRegisterCancel.setOnClickListener(view -> {
            registerRentName.setText("");
            registerRentAddress.setText("");
            registerRentPhone.setText("");
            buttonCreateRenter.setVisibility(View.VISIBLE);
            cardRegisterRenter.setVisibility(View.GONE);
        });

        buttonRegisterRenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Renter renter = requestRenter();

            }
        });
    }

    protected Renter requestRenter() {
        mApiService.registerRenter(MainActivity.cookies.id, registerRentName.getText().toString(), registerRentAddress.getText().toString(), registerRentPhone.getText().toString()).enqueue(new Callback<Renter>() {
            @Override
            public void onResponse(Call<Renter> call, Response<Renter> response) {
                if (response.isSuccessful()) {
                    MainActivity.cookies.renter = response.body();

                    Toast.makeText(mContext, "Register Successfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Renter> call, Throwable t) {
                Toast.makeText(mContext, "Account Already Exist", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }


}