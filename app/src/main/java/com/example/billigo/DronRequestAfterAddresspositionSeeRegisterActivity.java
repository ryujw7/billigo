package com.example.billigo;

import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Toast;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;

public class DronRequestAfterAddresspositionSeeRegisterActivity extends BaseActivity implements OnMapReadyCallback {
    Double latitude;
    Double longtitude;
    NaverMapOptions options;
    MapFragment mapFragment;
    Marker marker;
    BottomSheetDialog bottomSheetDialog;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone_request_after_addressposition_see_register);
        context = this;
        Intent intent = getIntent();
        latitude = intent.getDoubleExtra("latitude", 0);
        longtitude = intent.getDoubleExtra("longtitude", 0);
        options = new NaverMapOptions()
                .camera(new CameraPosition(new LatLng(latitude, longtitude), 16))
                .mapType(NaverMap.MapType.Hybrid);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance(options);
            getSupportFragmentManager().beginTransaction().add(R.id.navermap_map_view, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        marker = new Marker();
        marker.setPosition(new LatLng(latitude, longtitude));
        marker.setOnClickListener(new Overlay.OnClickListener() {
            @Override
            public boolean onClick(@NonNull Overlay overlay) {
                bottomSheetDialog = BottomSheetDialog.getInstance();
                bottomSheetDialog.show(getSupportFragmentManager(),"bottomSheet");
                return true;
            }
        });
        marker.setMap(naverMap);
        naverMap.setOnMapClickListener(new NaverMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull PointF pointF, @NonNull LatLng latLng) {            }
        });
    }
}