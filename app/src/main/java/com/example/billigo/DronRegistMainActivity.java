package com.example.billigo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DronRegistMainActivity extends BaseActivity {
    public static Activity dronregmainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone_regist_main);
        dronregmainActivity = DronRegistMainActivity.this;
        findViewById(R.id.firstregist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DronRegistMainActivity.this, DronRegistFirstRegistActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        /*findViewById(R.id.alreadyregist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DronRegistMainActivity.this, DronRegistFirstRegistActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });*/
    }
}
