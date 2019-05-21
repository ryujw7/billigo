package com.example.billigo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.naver.maps.geometry.LatLng;


import java.text.DecimalFormat;

public class BottomSheetDialog extends BottomSheetDialogFragment implements View.OnClickListener {
    ImageButton requestbtn;
    TextView distanceView;
    LatLng nowAddr;
    LatLng regAddr;
    int distance;
    DronRequestAfterAddresspositionSeeRegisterActivity activity;

    public static BottomSheetDialog getInstance() {
        return new BottomSheetDialog();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = (DronRequestAfterAddresspositionSeeRegisterActivity) DronRequestAfterAddresspositionSeeRegisterActivity.dronRequestAfterAddresspositionSeeRegisterActivity;
        View view = inflater.inflate(R.layout.activity_drone_request_after_addressposition_see_register_previewlayout, container, false);
        requestbtn = (ImageButton) view.findViewById(R.id.showrequestinfo);
        distanceView = (TextView) view.findViewById(R.id.distance);
        distance = CalculationByDistance(nowAddr, regAddr);
        distanceView.setText(Integer.toString(distance) + "미터");
        requestbtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.showrequestinfo:
                Intent intent = new Intent(activity.context, DronRequestClickRegisterClickMoreCheckActivity.class);
                startActivity(intent);
                break;
        }
        dismiss();
    }

    public void setAddr(LatLng p1, LatLng p2) {
        nowAddr = p1;
        regAddr = p2;
    }

    public static int CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult * 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        return meterInDec;
    }
}
