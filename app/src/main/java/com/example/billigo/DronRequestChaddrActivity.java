package com.example.billigo;

import android.os.Bundle;
import android.view.View;

public class DronRequestChaddrActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone_request_changeaddr);
        findViewById(R.id.adrc_chgregistbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
