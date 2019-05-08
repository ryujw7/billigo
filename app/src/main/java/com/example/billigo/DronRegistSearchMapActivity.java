package com.example.billigo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.view.View;

import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;

public class DronRegistSearchMapActivity extends BaseActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dron_regist_searchmap);
        MapFragment searchmap = (MapFragment)getSupportFragmentManager().findFragmentById(R.id.search_map_view);
        if(searchmap == null) {
            searchmap = MapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.search_map_view,searchmap);
        }
        searchmap.getMapAsync(this);
        findViewById(R.id.adrs_searchfinish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }
    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        naverMap.setMapType(NaverMap.MapType.Hybrid);
        //...
    }
}
