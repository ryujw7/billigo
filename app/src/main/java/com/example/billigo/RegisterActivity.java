package com.example.billigo;
/*
 * 회원가입 양식입력 액티비티
 * made by 오늘도 지구인
 * */

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends BaseActivity implements OnCheckedChangeListener {
    CheckBox allagree;
    CheckBox agree1;
    CheckBox agree2;
    CheckBox agree3;
    String id;
    EditText register_id;
    EditText register_pw;
    EditText register_pw_repeat;
    EditText register_name;
    EditText register_birthday;
    EditText register_email;
    RadioGroup radioGroup;
    RadioButton register_gender_man;
    RadioButton register_gender_girl;
    Button email_list_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        allagree = (CheckBox) findViewById(R.id.register_allagree);
        agree1 = (CheckBox) findViewById(R.id.register_agree1);
        agree2 = (CheckBox) findViewById(R.id.register_agree2);
        agree3 = (CheckBox) findViewById(R.id.register_agree3);
        register_id = (EditText) findViewById(R.id.register_id);
        register_name = (EditText) findViewById(R.id.register_name);
        register_pw = (EditText) findViewById(R.id.register_pw);
        register_pw_repeat = (EditText) findViewById(R.id.register_repw);
        radioGroup = (RadioGroup) findViewById(R.id.register_genradio);
        register_gender_man = (RadioButton) findViewById(R.id.isman);
        register_gender_girl = (RadioButton) findViewById(R.id.isgirl);
        register_birthday = (EditText) findViewById(R.id.register_birthday);
        register_email = (EditText) findViewById(R.id.register_email);
        email_list_btn = (Button) findViewById(R.id.register_list);
        email_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                getMenuInflater().inflate(R.menu.email_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String selectedemail = item.getTitle().toString();
                        switch (selectedemail) {
                            case "구글":
                                email_list_btn.setText("google.com");
                                break;
                            case "네이버":
                                email_list_btn.setText("naver.com");
                                break;
                            case "다음(한메일)":
                                email_list_btn.setText("hanmail.net");
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        allagree.setOnCheckedChangeListener(this);
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(agree1.isChecked()&&agree2.isChecked()&&agree3.isChecked())) {
                    Toast.makeText(getApplicationContext(), "약관에 모두 동의해주세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (register_id.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "아이디를 입력해주세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(register_pw.getText().length() < 4) {
                    Toast.makeText(getApplicationContext(), "비밀번호는 입력해주세요.(4자리이상)", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(register_pw.getText().toString() != register_pw_repeat.getText().toString()) {
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(register_gender_girl.isChecked() || register_gender_man.isChecked()) {
                    Toast.makeText(getApplicationContext(), "성별을 선택해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(register_birthday.getText().length() != 8) {
                    Toast.makeText(getApplicationContext(), "생년월일 8자리 입력해주세요! (예:19940706)", Toast.LENGTH_SHORT).show();
                    return;
                }
                setResult(RESULT_OK);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (allagree.isChecked()) {
            agree1.setChecked(true);
            agree2.setChecked(true);
            agree3.setChecked(true);
        } else {
            agree1.setChecked(false);
            agree2.setChecked(false);
            agree3.setChecked(false);
        }
    }
}
