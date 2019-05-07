package com.example.billigo;

import android.os.Bundle;
import android.view.View;

public class DronRegistResultActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone_regist_registresult);
        findViewById(R.id.finishregistbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
