package com.example.billigo;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class DronRegistFirstRegistActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dron_regist_firstregist);
        final CheckBox checkBox1 = (CheckBox)findViewById(R.id.infoagree);
        final CheckBox checkBox2 = (CheckBox)findViewById(R.id.tradeagree);
        final CheckBox checkBox3 = (CheckBox)findViewById(R.id.droninfoagree);
        final EditText editText = (EditText)findViewById(R.id.registname);
        findViewById(R.id.tradelocationregbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DronRegistFirstRegistActivity.this, DronRegistTradeAddrActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        findViewById(R.id.regaccountbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DronRegistFirstRegistActivity.this, DronRegistRegAccountActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        findViewById(R.id.dronlicenseregbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DronRegistFirstRegistActivity.this, DronRegistDronLicenseActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        findViewById(R.id.nextbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox1.isChecked() && checkBox2.isChecked() && checkBox3.isChecked()) {
                        if (editText.getText().toString().length() == 0) {
                            Toast.makeText(DronRegistFirstRegistActivity.this, "닉네임을 적어주세요.", Toast.LENGTH_SHORT).show();
                        } else {
                            finish();
                            //startActivity(new Intent(DronRegistFirstRegistActivity.this, DronRegistRegAccountActivity.class));
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                } else {
                    Toast.makeText(DronRegistFirstRegistActivity.this, "약관에 동의해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.backbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
