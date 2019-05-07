package com.example.billigo;

import android.os.Bundle;
import android.view.View;

public class DronRequestClickRegisterCheckActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dron_request_click_register_check);
        findViewById(R.id.adrcrc_backbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.adrcrc_requestbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
