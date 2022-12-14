package com.BismaAlifAlghifariJSleepMN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.BismaAlifAlghifariJSleepMN.jsleep_android.model.Facility;
import com.BismaAlifAlghifariJSleepMN.jsleep_android.model.Room;

/**
 * The {@code RoomDetailActivity} class provides the UX for displaying the details of a room.
 *
 * @author Bisma Alif Alghifari
 * @version 1.0
 *
 */
public class DetailRoomActivity extends AppCompatActivity {

    TextView showName, showPrice, showSize, showAddress, showBedtype, showCity;
    /**
     * The {@link RadioButton} that displays the room's facility : ac, refrigerator, bath tub, balcony
     * restaurant, pool, and fitness center.
     */
    RadioButton ac, refrig, wifi, bathub, balcony, restaurant, pool, fitness;

    /**
     * Button for booking a room.
     */
    Button buttonBookNow;

    /**
     * The renter's room information.
     */
    public static Room room;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_room);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        room = MainActivity.listRoom.get(MainActivity.roomIndex);

        buttonBookNow = findViewById(R.id.detailBooknowButton);

        showName = findViewById(R.id.roomNameVar);
        showPrice = findViewById(R.id.roomPriceVar);
        showSize = findViewById(R.id.roomSizeVar);
        showAddress = findViewById(R.id.roomAddressVar);
        showBedtype = findViewById(R.id.roomBedTypeVar);
        showCity = findViewById(R.id.roomCityVar);

        ac = findViewById(R.id.ac);
        refrig = findViewById(R.id.refrigerator);
        wifi = findViewById(R.id.wifi);
        bathub = findViewById(R.id.bathub);
        balcony = findViewById(R.id.balcony);
        restaurant = findViewById(R.id.restaurant);
        pool = findViewById(R.id.pool);
        fitness = findViewById(R.id.fitness);


        String City = String.valueOf(room.city);
        showCity.setText(City);

        showName.setText(room.name);
        String price = "Rp. " + String.valueOf(room.price.price + "/ Night");
        showPrice.setText(price);
        String size = String.valueOf(room.size) + " M";
        showSize.setText(size);
        showAddress.setText(room.address);
//        showBedtype.setText(room.bedType.toString());


        String finalBed = room.bedType.toString() + " BED";
        System.out.println(finalBed);
        showBedtype.setText(finalBed);

        for (int i = 0; i < room.facility.size(); i++) {
            if (room.facility.get(i).equals(Facility.AC )) {
                ac.setChecked(true);
            } else if (room.facility.get(i).equals(Facility.Refrigerator)) {
                refrig.setChecked(true);
            } else if (room.facility.get(i).equals(Facility.WiFi)) {
                wifi.setChecked(true);
            } else if (room.facility.get(i).equals(Facility.Bathtub)) {
                bathub.setChecked(true);
            } else if (room.facility.get(i).equals(Facility.Balcony)) {
                balcony.setChecked(true);
            } else if (room.facility.get(i).equals(Facility.Restaurant)) {
                restaurant.setChecked(true);
            } else if (room.facility.get(i).equals(Facility.SwimmingPool)) {
                pool.setChecked(true);
            } else if (room.facility.get(i).equals(Facility.FitnessCenter)) {
                fitness.setChecked(true);
            }
        }

        buttonBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(DetailRoomActivity.this, CreatePaymentActivity.class);
                startActivity(move);
            }
        });


    }

}