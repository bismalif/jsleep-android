package com.BismaAlifAlghifariJSleepMN.jsleep_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.BismaAlifAlghifariJSleepMN.jsleep_android.model.Account;
import com.BismaAlifAlghifariJSleepMN.jsleep_android.model.Room;
import com.BismaAlifAlghifariJSleepMN.jsleep_android.request.BaseApiService;
import com.BismaAlifAlghifariJSleepMN.jsleep_android.request.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static Account cookies;

    BaseApiService mApiService;
    Context mContext;
    ListView listView;
    List<Room> activitylist;
    TextView home, search, profile, createRoom;
    public static ArrayList<Room> listRoom;
    public static int roomIndex;
    int current;
    Button next, prev;
    int currentPage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        current = 0;
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        setContentView(R.layout.activity_main);

        mApiService = UtilsApi.getApiService();
        mContext = this;
        activitylist = getRoomList(current);

        next = findViewById(R.id.prev);
        prev = findViewById(R.id.next);
        listView = (ListView) findViewById(R.id.listviewer);
        listView.setOnItemClickListener(this::onItemClick);

        home = findViewById(R.id.homeButton);
        search = findViewById(R.id.searchButton);
        profile = findViewById(R.id.accountButton);
        createRoom = findViewById(R.id.addButton);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent (MainActivity.this, AboutMeActivity.class);
                startActivity(move);
            }
        });

        createRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent (MainActivity.this, CreateRoomActivity.class);
                startActivity(move);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current += 1;
                activitylist = getRoomList(current);
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(current == 0){
                    Toast.makeText(mContext, "You are at the first page", Toast.LENGTH_SHORT).show();
                }
                else{
                    current -= 1;
                    activitylist = getRoomList(current);
                }
            }
        });


    }
//        try {
//            filepath = getAssets().open("randomRoomList.json");
//            BufferedReader reader = new BufferedReader(new InputStreamReader(filepath));
//            Room[] acc = gson.fromJson(reader, Room[].class);
//            Collections.addAll(roomList, acc);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        for (Room room : roomList) {
//            name.add(room.name);
//        }
//        ArrayAdapter<String> roomAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, name);
//        ListView view = (ListView) findViewById(R.id.listviewer);
//        view.setAdapter(roomAdapter);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.account_button:
                Intent move = new Intent(MainActivity.this, AboutMeActivity.class);
                startActivity(move);
                return true;
            case R.id.add_button:
                Intent go = new Intent(MainActivity.this, CreateRoomActivity.class);
                startActivity(go);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuItem register = menu.findItem(R.id.add_button);
        if(cookies.renter == null){
            register.setVisible(false);
        }
        else {
            register.setVisible(true);
        }
        return true;
    }




    /**
     * This Function is used to get the list of room
     * @param page is the page number
     * @return the list of room
     * @see Room
     * @author Bisma Alif Alghifari
     */

    protected List<Room> getRoomList(int page) {
        //System.out.println(pageSize);
        mApiService.getAllRoom(page, 5).enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                if (response.isSuccessful()) {
                    activitylist = response.body();
                    assert activitylist != null;
                    listRoom = new ArrayList<Room>(activitylist);
                    CustomListAdapter adapter = new CustomListAdapter(mContext, listRoom);
                    listView.setAdapter(adapter);
                    System.out.println("Get Room Success");


                }
            }
            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(mContext, "get room failed", Toast.LENGTH_SHORT).show();
            }

        });
        return null;
    }

    /**
     *
     * @param v is the view
     * @param position is the position of the room
     * @param id is the id of the room
     * @author Bisma Alif Alghifari
     */

    public void onItemClick (AdapterView<?> l, View v, int position, long id){
        System.out.println("onItemClick Success");
        Log.i("ListView", "You clicked Item np : " + id + " at position:" + position);
        // Then you start a new Activity via Intent
        Intent intent = new Intent(this, DetailRoomActivity.class);
        roomIndex = position;
        System.out.println("clicked");
        intent.setClass(mContext, DetailRoomActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("id", id);
        startActivity(intent);
    }

}