package com.example.billigo;
/*
 * 회원가입 양식입력 액티비티
 * made by 오늘도 지구인
 * */
import android.os.Bundle;
import android.view.View;

public class RegisterActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
