package com.example.billigo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DronRegistRegAccountActivity extends BaseActivity {
    EditText name;
    EditText number;
    EditText phone;
    EditText certnum;
    Button bankbtn;
    int REQ_BANK = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone_regist_regaccount);
        name = (EditText)findViewById(R.id.adrraccname);
        number = (EditText)findViewById(R.id.adrraccnumber);
        phone = (EditText)findViewById(R.id.adrrphonenum);
        certnum = (EditText)findViewById(R.id.adrrcertnum);
        bankbtn = (Button)findViewById(R.id.adrraccbank);
        bankbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DronRegistRegAccountActivity.this, BankGridViewActivity.class);
                startActivityForResult(intent, REQ_BANK);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        findViewById(R.id.adrrbackbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        findViewById(R.id.adrrfinish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "예금주를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    if(number.getText().toString().length() == 0) {
                        Toast.makeText(getApplicationContext(), "계좌번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    } else {
                        if(phone.getText().toString().length() == 0) {
                            Toast.makeText(getApplicationContext(), "휴대폰 번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                        } else {
                            if(certnum.getText().toString().length() == 0) {
                                Toast.makeText(getApplicationContext(), "인증번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                            } else {
                                setResult(RESULT_OK);
                                finish();
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_BANK) {
            if(resultCode == RESULT_OK) {
                String bankname = data.getStringExtra("bank");
                bankbtn.setText(bankname);
            }
        }
    }
}
