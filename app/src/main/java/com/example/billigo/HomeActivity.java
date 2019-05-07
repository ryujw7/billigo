package com.example.billigo;

/*
 * 초기 화면 액티비티
 * made by 오늘도 지구인
 * */

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

import org.json.JSONObject;

public class HomeActivity extends BaseActivity {
    private boolean isLogin = false;
    private OAuthLoginButton naverLoginButton;
    private static OAuthLogin naverLoginInstance;
    static final String CLIENT_ID = "GCwVPUMzNoVnZDTTCKlf";
    static final String CLIENT_SECRET = "dnpMDJGUAP";
    static final String CLIENT_NAME = "로그인 테스트";
    String id;
    String pw;
    static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        init_View();
        final EditText id_home = (EditText)findViewById(R.id.home_id);
        final EditText pw_home = (EditText)findViewById(R.id.home_pw);
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
        if (naverLoginInstance.getState(context) != OAuthLoginState.NEED_LOGIN) {
            naverLoginInstance.logout(context);
            setResult(RESULT_OK);
            finish();
        }
        naverLoginInstance = OAuthLogin.getInstance();
        naverLoginInstance.init(this,
                CLIENT_ID,
                CLIENT_SECRET,
                CLIENT_NAME);
    }

    private void init_View() {
        naverLoginButton = (OAuthLoginButton) findViewById(R.id.naverLogin);
        OAuthLoginHandler naverLoginHandler = new OAuthLoginHandler() {
            @Override
            public void run(boolean b) {
                if (b) {
                    setResult(RESULT_OK);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
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

    private class RequestApiTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {//작업이 실행되기 전에 먼저 실행.
        }

        @Override
        protected String doInBackground(Void... params) {//네트워크에 연결하는 과정이 있으므로 다른 스레드에서 실행되어야 한다.
            String url = "https://openapi.naver.com/v1/nid/me";
            String at = naverLoginInstance.getAccessToken(context);
            return naverLoginInstance.requestApi(context, at, url);//url, 토큰을 넘겨서 값을 받아온다.json 타입으로 받아진다.
        }

        protected void onPostExecute(String content) {//doInBackground 에서 리턴된 값이 여기로 들어온다.
            try {
                JSONObject jsonObject = new JSONObject(content);
                JSONObject response = jsonObject.getJSONObject("response");
                String email = response.getString("email");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(HomeActivity.this, MainActivity.class));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
