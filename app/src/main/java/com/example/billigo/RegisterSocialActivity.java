package com.example.billigo;
/*
 * 회원가입 양식입력 액티비티
 * made by 오늘도 지구인
 * */

import android.content.Intent;
import android.graphics.Color;
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

public class RegisterSocialActivity extends BaseActivity implements OnCheckedChangeListener {
    CheckBox allagree;
    CheckBox agree1;
    CheckBox agree2;
    CheckBox agree3;
    String id;
    String[] id_arr;
    String name;
    String birthday;
    String gender;
    String email;
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
        setContentView(R.layout.activity_register_social);
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
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        birthday = intent.getStringExtra("birthday");
        gender = intent.getStringExtra("gender");
        email = intent.getStringExtra("email");
        register_name.setText(name);
        register_name.setEnabled(false);
        register_name.setTextColor(Color.parseColor("#000000"));
        if (gender.matches("F")) {
            register_gender_girl.setChecked(true);
            register_gender_girl.setEnabled(false);
            register_gender_man.setEnabled(false);
            register_gender_girl.setTextColor(Color.parseColor("#000000"));
            register_gender_man.setTextColor(Color.parseColor("#000000"));
        } else if (gender.matches("M")) {
            register_gender_man.setChecked(true);
            register_gender_girl.setEnabled(false);
            register_gender_man.setEnabled(false);
            register_gender_girl.setTextColor(Color.parseColor("#000000"));
            register_gender_man.setTextColor(Color.parseColor("#000000"));
        }
        id_arr = email.split("@");
        id = id_arr[0];
        register_email.setText(id);
        register_email.setEnabled(false);
        register_email.setTextColor(Color.parseColor("#000000"));
        email_list_btn.setText("naver.com");
        email_list_btn.setEnabled(false);
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
                if(!(register_pw.getText().toString().matches(register_pw_repeat.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
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
