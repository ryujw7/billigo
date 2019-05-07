package com.example.billigo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DronRegistRegAccountActivity extends BaseActivity {
    EditText name;
    EditText number;
    EditText phone;
    EditText certnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone_regist_regaccount);
        name = (EditText)findViewById(R.id.adrraccname);
        number = (EditText)findViewById(R.id.adrraccnumber);
        phone = (EditText)findViewById(R.id.adrrphonenum);
        certnum = (EditText)findViewById(R.id.adrrcertnum);
        findViewById(R.id.adrrfinish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString() == "") {
                    Toast.makeText(getApplicationContext(), "예금주를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    if(number.getText().toString() == "") {
                        Toast.makeText(getApplicationContext(), "계좌번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    } else {
                        if(phone.getText().toString() == "") {
                            Toast.makeText(getApplicationContext(), "휴대폰 번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                        } else {
                            if(certnum.getText().toString() == "") {
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
}
