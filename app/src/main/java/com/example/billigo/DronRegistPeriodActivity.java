package com.example.billigo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DronRegistPeriodActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dron_regist_period);


        findViewById(R.id.adrp_nextbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DronRegistPeriodActivity.this, DronRegistPaymentActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
}
