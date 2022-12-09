package com.BismaAlifAlghifariJSleepMN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.BismaAlifAlghifariJSleepMN.jsleep_android.model.Facility;
import com.BismaAlifAlghifariJSleepMN.jsleep_android.model.Room;

public class DetailRoomActivity extends AppCompatActivity {

    TextView showName, showPrice, showSize, showAddress, showBedtype;
    RadioButton ac, refrig, wifi, bathub, balcony, restaurant, pool, fitness;
    public static Room room = MainActivity.listRoom.get(MainActivity.roomIndex);


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_room);


        showName = findViewById(R.id.roomNameVar);
        showPrice = findViewById(R.id.roomPriceVar);
        showSize = findViewById(R.id.roomSizeVar);
        showAddress = findViewById(R.id.roomAddressVar);
        showBedtype = findViewById(R.id.roomBedTypeVar);

        ac = findViewById(R.id.ac);
        refrig = findViewById(R.id.refrigerator);
        wifi = findViewById(R.id.wifi);
        bathub = findViewById(R.id.bathub);
        balcony = findViewById(R.id.balcony);
        restaurant = findViewById(R.id.restaurant);
        pool = findViewById(R.id.pool);
        fitness = findViewById(R.id.fitness);

        showName.setText(room.name);
        showPrice.setText(String.valueOf(room.price.price));
        showSize.setText(String.valueOf(room.size));
        showAddress.setText(room.address);
        showBedtype.setText(room.bedType.toString());

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

    }

}