package com.example.billigo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class DronRegistFirstRegistActivity extends BaseActivity {
    final int REQUEST_TRADE_ADDR = 2;
    final int REQUEST_ACCOUNT = 3;
    final int REQUEST_DRON_LISENCE = 4;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    EditText editText;
    Button regaccbtn;
    Button dronliregbtn;
    Button tradeaddrbtn;
    CheckBox lisenceCheck;
    public static Activity firstregActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone_regist_firstregist);
        firstregActivity = DronRegistFirstRegistActivity.this;
        checkBox1 = (CheckBox)findViewById(R.id.infoagree);
        checkBox2 = (CheckBox)findViewById(R.id.tradeagree);
        checkBox3 = (CheckBox)findViewById(R.id.droninfoagree);
        editText = (EditText)findViewById(R.id.registname);
        regaccbtn = (Button)findViewById(R.id.regaccountbtn);
        dronliregbtn = (Button)findViewById(R.id.dronlicenseregbtn);
        tradeaddrbtn = (Button)findViewById(R.id.tradelocationregbtn);
        lisenceCheck = (CheckBox)findViewById(R.id.lisenceCheck);
        lisenceCheck.setClickable(false);
        lisenceCheck.setChecked(false);
        findViewById(R.id.tradelocationregbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DronRegistFirstRegistActivity.this, DronRegistTradeAddrActivity.class);
                startActivityForResult(intent,REQUEST_TRADE_ADDR);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        findViewById(R.id.regaccountbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DronRegistFirstRegistActivity.this, DronRegistRegAccountActivity.class);
                startActivityForResult(intent, REQUEST_ACCOUNT);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        findViewById(R.id.dronlicenseregbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DronRegistFirstRegistActivity.this, DronRegistDronLicenseActivity.class);
                startActivityForResult(intent, REQUEST_DRON_LISENCE);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        findViewById(R.id.adrdnextbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox1.isChecked() && checkBox2.isChecked() && checkBox3.isChecked()) {
                        if (editText.getText().toString().length() == 0) {
                            Toast.makeText(DronRegistFirstRegistActivity.this, "닉네임을 적어주세요.", Toast.LENGTH_SHORT).show();
                        } else {
                            if(tradeaddrbtn.getText().toString().length() == 0) {
                                Toast.makeText(DronRegistFirstRegistActivity.this, "희망거래지역을 등록해주세요", Toast.LENGTH_SHORT).show();
                            } else {
                                if (regaccbtn.getText().toString().length() == 0) {
                                    Toast.makeText(DronRegistFirstRegistActivity.this, "계좌정보를 등록해주세요", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (dronliregbtn.getText().toString().length() == 0) {
                                        Toast.makeText(DronRegistFirstRegistActivity.this, "드론자격증번호를 등록해주세요", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Intent intent = new Intent(DronRegistFirstRegistActivity.this, DronRegistDronActivity.class);
                                        startActivity(intent);
                                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                    }
                                }
                            }
                        }
                } else {
                    Toast.makeText(DronRegistFirstRegistActivity.this, "약관에 동의해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.adrdbackbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_TRADE_ADDR) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(DronRegistFirstRegistActivity.this, "주소 등록 완료", Toast.LENGTH_SHORT).show();
                String getText = data.getStringExtra("result");
                tradeaddrbtn.setText(getText + "\n(변경하려면 다시 클릭)");
                onResume();
            }
        } else if(requestCode == REQUEST_DRON_LISENCE) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(DronRegistFirstRegistActivity.this, "자격증 등록 완료", Toast.LENGTH_SHORT).show();
                lisenceCheck.setChecked(true);
                onResume();
            }
        }  else if(requestCode == REQUEST_ACCOUNT) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(DronRegistFirstRegistActivity.this, "계좌 등록 완료", Toast.LENGTH_SHORT).show();
                regaccbtn.setText("등록완료\n(변경하려면 다시 클릭)");
                onResume();
            }
        }
    }
}
