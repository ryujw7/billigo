package com.example.billigo;

import android.os.Bundle;
import android.view.View;

public class DronRequestChaddrActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        setContentView(R.layout.activity_drone_request_changeaddr);
=======
        setContentView(R.layout.activity_dron_request_changeaddr);
        findViewById(R.id.adrc_chgregistbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
>>>>>>> 87046d3f07897b3ac6a9aedb8651ce14780ff6df
    }
}
