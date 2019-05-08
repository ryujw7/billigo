package com.example.billigo;

import android.os.Bundle;
import android.view.View;

public class DronRegistResultActivity extends BaseActivity {
    DronRegistMainActivity zero = (DronRegistMainActivity)DronRegistMainActivity.dronregmainActivity;
    DronRegistFirstRegistActivity first = (DronRegistFirstRegistActivity)DronRegistFirstRegistActivity.firstregActivity;
    DronRegistDronActivity second = (DronRegistDronActivity)DronRegistDronActivity.dronregdronActivity;
    DronRegistPeriodActivity third = (DronRegistPeriodActivity)DronRegistPeriodActivity.dronregperiodActivity;
    DronRegistPaymentActivity fourth = (DronRegistPaymentActivity)DronRegistPaymentActivity.dronregpayActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone_regist_registresult);
        findViewById(R.id.finishregistbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zero.finish();
                first.finish();
                second.finish();
                third.finish();
                fourth.finish();
                finish();
            }
        });
    }
}
