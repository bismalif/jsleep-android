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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.BismaAlifAlghifariJSleepMN.jsleep_android.model.Renter;
import com.BismaAlifAlghifariJSleepMN.jsleep_android.request.BaseApiService;
import com.BismaAlifAlghifariJSleepMN.jsleep_android.request.UtilsApi;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AboutMeActivity extends AppCompatActivity {
    TextView name, email, balance;
    EditText registerRentName,registerRentAddress, registerRentPhone;
    EditText renterName, renterAddress, renterPhone, topUpBalance;
    Button buttonRegisterCancel, buttonCreateRenter, buttonRegisterRenter, topUpButton;
    LinearLayout cardRenterDetails, cardRegisterRenter, cardAccount, cardButtonCreateRenter;
    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        //TopUp
        topUpButton = findViewById(R.id.topUpBtn);
        topUpBalance = findViewById(R.id.Topuptxt);

        //Acc Details
        name = findViewById(R.id.detailName);
        email = findViewById(R.id.detailEmail);
        balance = findViewById(R.id.detailBalance);

        name.setText(MainActivity.cookies.name);
        email.setText(MainActivity.cookies.email);
        String balanceText = "Rp. " + String.valueOf(MainActivity.cookies.balance);
        balance.setText(balanceText);

        //Button Reg
        buttonCreateRenter = findViewById(R.id.buttonRegisterRenter);
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
        cardButtonCreateRenter = findViewById(R.id.registerRenter);
        cardAccount = findViewById(R.id.cardAccount);
        cardRegisterRenter = findViewById(R.id.cardRegisterRenter);
        cardRenterDetails = findViewById(R.id.cardRenterDetails);


        cardRegisterRenter.setVisibility(View.GONE);
        cardRenterDetails.setVisibility(View.GONE);

        topUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopUp(MainActivity.cookies.id,Double.parseDouble(topUpBalance.getText().toString()));
            }
        });

        if (MainActivity.cookies.renter == null) {
            cardButtonCreateRenter.setVisibility(View.VISIBLE);
            cardRenterDetails.setVisibility(View.GONE);
            cardRegisterRenter.setVisibility(View.GONE);


            buttonCreateRenter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buttonCreateRenter.setVisibility(View.GONE);
                    cardRenterDetails.setVisibility(View.GONE);
                    cardRegisterRenter.setVisibility(View.VISIBLE);


                    buttonRegisterRenter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            System.out.println(renterName);
                            Renter renter = requestRenter(MainActivity.cookies.id, registerRentName.getText().toString(), registerRentAddress.getText().toString(), registerRentPhone.getText().toString());



                            cardRenterDetails.setVisibility(View.VISIBLE);
                            cardRegisterRenter.setVisibility(View.GONE);

                        }
                    });

                    buttonRegisterCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            buttonCreateRenter.setVisibility(View.VISIBLE);
                            cardRegisterRenter.setVisibility(View.GONE);
                        }
                    });



                }
            });
        }
        else {
            buttonCreateRenter.setVisibility(View.GONE);
            cardRenterDetails.setVisibility(View.VISIBLE);
            cardRegisterRenter.setVisibility(View.GONE);

            renterName.setText(MainActivity.cookies.renter.username);
            renterAddress.setText(MainActivity.cookies.renter.address);
            renterPhone.setText(MainActivity.cookies.renter.phoneNumber);
        }
    }

    protected Renter requestRenter(int id, String username, String address, String phoneNumber ) throws NullPointerException {
        System.out.println("Id: " + id);
        System.out.println("Username: " + username);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phoneNumber);
        mApiService.renter(id, username, address, phoneNumber).enqueue(new Callback<Renter>() {
            @Override
            public void onResponse(Call<Renter> call, Response<Renter> response) {
                if(response.isSuccessful()){
                    Renter renter = response.body();
                    MainActivity.cookies.renter = renter;
                    Toast.makeText(mContext, "Register Renter Successful", Toast.LENGTH_SHORT).show();
                    Intent move = new Intent(AboutMeActivity.this, AboutMeActivity.class);
                    startActivity(move);


                }
            }

            @Override
            public void onFailure(Call<Renter> call, Throwable t) {
                Toast.makeText(mContext, "Register Renter Failed", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }


    protected Renter TopUp(int id, double balance) {

        mApiService.topUp(id, balance).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    // Update the TextView with the new balance
                    MainActivity.cookies.balance = MainActivity.cookies.balance + balance;
                    System.out.println("BALANCE ADDED");
                    Toast.makeText(mContext, "Top Up Successful!", Toast.LENGTH_LONG).show();


                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                // Handle the failure
                System.out.println("BALANCE FAILED TO ADD");
                Toast.makeText(mContext, "Top Up Failed!", Toast.LENGTH_LONG).show();
            }
        });
        return null;
    }
}

