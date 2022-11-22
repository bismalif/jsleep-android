package com.BismaAlifAlghifariJSleepMN.jsleep_android.request;

import com.BismaAlifAlghifariJSleepMN.jsleep_android.model.Account;
import com.BismaAlifAlghifariJSleepMN.jsleep_android.model.Room;

import java.net.PasswordAuthentication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BaseApiService {
    @GET("account/{id}")
    Call<Account> getAccount (@Path("id") int id);

    @POST("account/login")
    Call<Account> login (@Query("email") String email, @Query("password") String Password);

    @GET("room/{id}")
    Call<Room> getRoom (@Path("id") int id);

    @POST("account/register")
    Call<Account> register  (@Query("name") String name,
                             @Query("email") String email,
                             @Query("password") String password);



}
