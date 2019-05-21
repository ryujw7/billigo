package com.example.billigo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DronRegistDronActivity extends BaseActivity {
    public static Activity dronregdronActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone_regist_drone);
        dronregdronActivity = DronRegistDronActivity.this;
        findViewById(R.id.adrdbackbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.adrdnextbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DronRegistDronActivity.this, DronRegistPeriodActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }

}
