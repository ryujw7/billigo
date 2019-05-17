package com.example.billigo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.OnMapReadyCallback;

public class DronRequestAfterAddresspositionSeeRegisterActivity extends BaseActivity implements OnMapReadyCallback {
    Double latitude;
    Double longtitude;
    NaverMapOptions options;
    MapFragment mapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone_request_after_addressposition_see_register);
        Intent intent = getIntent();
        latitude = intent.getDoubleExtra("latitude",0);
        longtitude = intent.getDoubleExtra("longtitude",0);
        options = new NaverMapOptions()
                .camera(new CameraPosition(new LatLng(latitude, longtitude),16))
                .mapType(NaverMap.MapType.Hybrid);
        if(mapFragment == null) {
            mapFragment = MapFragment.newInstance(options);
            getSupportFragmentManager().beginTransaction().add(R.id.navermap_map_view, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
    }
}
