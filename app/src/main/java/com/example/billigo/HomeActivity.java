package com.example.billigo;

/*
 * 초기 화면 액티비티
 * made by 오늘도 지구인
 * */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.data.OAuthLoginState;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends BaseActivity {
    public static Activity homeactivity;
    private boolean isLogin = false;
    private OAuthLoginButton naverLoginButton;
    private static OAuthLogin naverLoginInstance;
    static final String CLIENT_ID = "GCwVPUMzNoVnZDTTCKlf";
    static final String CLIENT_SECRET = "dnpMDJGUAP";
    static final String CLIENT_NAME = "로그인 테스트";
    String id;
    String pw;
    static Context context;
    String name;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homeactivity = (Activity)HomeActivity.this;
        init();
        init_View();
        final EditText id_home = (EditText) findViewById(R.id.home_id);
        final EditText pw_home = (EditText) findViewById(R.id.home_pw);
        id = id_home.getText().toString();
        pw = pw_home.getText().toString();
        findViewById(R.id.loginbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(id == "ryujw7"&&pw == "1111") {
                    setResult(RESULT_OK);
                } else {
                    setResult(RESULT_CANCELED);
                }*/
                setResult(RESULT_OK);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });
        findViewById(R.id.registerbuton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, RegisterActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });
        findViewById(R.id.searchaccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SearchidActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });
    }

    private void init() {
        context = this;
        naverLoginInstance = OAuthLogin.getInstance();
        naverLoginInstance.init(this,
                CLIENT_ID,
                CLIENT_SECRET,
                CLIENT_NAME);
        Intent intent = getIntent();
        if(intent.getBooleanExtra("need", true)) {
            naverLoginInstance.logout(context);
        }
        if (naverLoginInstance.getState(context) != OAuthLoginState.NEED_LOGIN) {
            naverLoginInstance.logout(context);
            setResult(RESULT_OK);
            finish();
        }
    }

    private void init_View() {
        naverLoginButton = (OAuthLoginButton) findViewById(R.id.naverLogin);
        OAuthLoginHandler naverLoginHandler = new OAuthLoginHandler() {
            @Override
            public void run(boolean b) {
                if (b) {
                    new Thread() {
                        public void run() {
                            String accessToken = naverLoginInstance.getAccessToken(context);
                            String url = "https://openapi.naver.com/v1/nid/me";
                            String data = naverLoginInstance.requestApi(context, accessToken, url);
                            try {
                                JSONObject result = new JSONObject(data);
                                name = result.getJSONObject("response").getString("name");
                                email = result.getJSONObject("response").getString("email");
                                Intent intent = new Intent();
                                intent.putExtra("name", name);
                                intent.putExtra("email",email);
                                setResult(RESULT_OK, intent);
                                finish();
                            } catch (JSONException e) {

                            }
                        }
                    }.start();
                } else {
                    setResult(RESULT_CANCELED);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    String errorCode = naverLoginInstance.getLastErrorCode(context).getCode();
                    String errorDesc = naverLoginInstance.getLastErrorDesc(context);
                    Toast.makeText(context, "errorCode:" + errorCode + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        };
        naverLoginButton.setOAuthLoginHandler(naverLoginHandler);
    }
}
