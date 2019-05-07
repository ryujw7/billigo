package com.example.billigo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DronRequestClickRegisterActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dron_request_click_register);
        findViewById(R.id.adrcr_requestbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DronRequestClickRegisterActivity.this, DronRequestClickRegisterCheckActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });
        findViewById(R.id.adrcr_backbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
