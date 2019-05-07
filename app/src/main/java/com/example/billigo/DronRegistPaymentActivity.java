package com.example.billigo;

import android.os.Bundle;
import android.view.View;

public class DronRegistPaymentActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone_regist_payment);
        findViewById(R.id.adrp_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
