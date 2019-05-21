package com.example.billigo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;

public class DronRequestAfterAddresspositionSeeRegisterActivity extends BaseActivity implements OnMapReadyCallback {
    public static Activity dronRequestAfterAddresspositionSeeRegisterActivity;
    Double latitude;
    Double longtitude;
    NaverMapOptions options;
    MapFragment mapFragment;
    Marker registermarker;
    BottomSheetDialog bottomSheetDialog;
    Context context;
    LatLng nowAddr;
    LatLng regAddr;
    LocationOverlay myposition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone_request_after_addressposition_see_register);
        dronRequestAfterAddresspositionSeeRegisterActivity = DronRequestAfterAddresspositionSeeRegisterActivity.this;
        context = this;
        Intent intent = getIntent();
        latitude = intent.getDoubleExtra("latitude", 0);
        longtitude = intent.getDoubleExtra("longtitude", 0);
        nowAddr = new LatLng(latitude, longtitude);
        regAddr = new LatLng(latitude - 0.01, longtitude - 0.01);
        options = new NaverMapOptions()
                .camera(new CameraPosition(nowAddr, 12))
                .mapType(NaverMap.MapType.Hybrid);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance(options);
            getSupportFragmentManager().beginTransaction().add(R.id.navermap_map_view, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        myposition = naverMap.getLocationOverlay();
        myposition.setCircleRadius(100);
        myposition.setVisible(true);
        registermarker = new Marker();
        registermarker.setPosition(regAddr);
        registermarker.setOnClickListener(new Overlay.OnClickListener() {
            @Override
            public boolean onClick(@NonNull Overlay overlay) {
                bottomSheetDialog = BottomSheetDialog.getInstance();
                bottomSheetDialog.setAddr(nowAddr, regAddr);
                bottomSheetDialog.show(getSupportFragmentManager(),"bottomSheet");
                return true;
            }
        });
        registermarker.setMap(naverMap);
        naverMap.setOnMapClickListener(new NaverMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull PointF pointF, @NonNull LatLng latLng) {            }
        });
    }

}