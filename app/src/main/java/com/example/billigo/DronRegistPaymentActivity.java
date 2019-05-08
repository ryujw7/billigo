package com.example.billigo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public class DronRegistPaymentActivity extends BaseActivity {
    public static Activity dronregpayActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone_regist_payment);
        dronregpayActivity = DronRegistPaymentActivity.this;
        findViewById(R.id.adrp_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DronRegistPaymentActivity.this, DronRegistResultActivity.class);
                startActivity(intent);
            }
        });
    }

}
