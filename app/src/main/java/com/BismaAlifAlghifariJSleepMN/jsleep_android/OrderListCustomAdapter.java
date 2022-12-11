package com.BismaAlifAlghifariJSleepMN.jsleep_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.BismaAlifAlghifariJSleepMN.jsleep_android.model.Payment;

import java.text.DateFormat;
import java.util.ArrayList;

public class OrderListCustomAdapter extends ArrayAdapter<Payment> {


    public OrderListCustomAdapter(@NonNull Context context, ArrayList<Payment> order) {
        super(context, 0, order);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View currentItemView = convertView;
        if(currentItemView == null){
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.order_list_custom_layout,parent,false);
        }

        Payment orderList = getItem(position);

        TextView date = currentItemView.findViewById(R.id.payment_date);
        TextView status = currentItemView.findViewById(R.id.payment_status);

        String dateText = DateFormat.getDateInstance().format(orderList.to) + " - " + DateFormat.getDateInstance().format(orderList.from);
        date.setText(dateText);

        String statusText = "Status: " + orderList.status;
        status.setText(statusText);


        return currentItemView;
    }


}