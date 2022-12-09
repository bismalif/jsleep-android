package com.BismaAlifAlghifariJSleepMN.jsleep_android.request;

import com.BismaAlifAlghifariJSleepMN.jsleep_android.model.Account;
import com.BismaAlifAlghifariJSleepMN.jsleep_android.model.BedType;
import com.BismaAlifAlghifariJSleepMN.jsleep_android.model.City;
import com.BismaAlifAlghifariJSleepMN.jsleep_android.model.Facility;
import com.BismaAlifAlghifariJSleepMN.jsleep_android.model.Renter;
import com.BismaAlifAlghifariJSleepMN.jsleep_android.model.Room;

import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;

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

    @POST("account/{id}/registerRenter")
    Call<Renter> renter(@Path("id") int id,
                                @Query("username") String username,
                                @Query("address") String address,
                                @Query("phoneNumber") String phoneNumber);

    @POST("account/{id}/topUp")
    Call<Boolean> topUp(@Path("id") int id,
                        @Query("balance") double balance);

    @POST("room/create")
    Call<Room> room(@Query("accountId") int accountId,
                    @Query("name") String name,
                    @Query("size") int size,
                    @Query("price") int price,
                    @Query("facility") ArrayList<Facility> facility,
                    @Query("city") City city,
                    @Query("address") String address,
                    @Query("bedType") BedType bedType);

    @GET("room/getAllRoom")
    Call<List<Room>> getAllRoom(@Query("page") int page,
                                @Query("pageSize") int pageSize);
}
