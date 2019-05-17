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
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.data.OAuthLoginState;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HomeActivity extends BaseActivity {
    public static Activity homeactivity;
    private boolean isLogin = false;
    private OAuthLoginButton naverLoginButton;
    private static OAuthLogin naverLoginInstance;
    private int REQUEST_SOCIAL_REGISTER = 10;
    static final String CLIENT_ID = "GCwVPUMzNoVnZDTTCKlf";
    static final String CLIENT_SECRET = "dnpMDJGUAP";
    static final String CLIENT_NAME = "로그인 테스트";
    EditText login_id;
    EditText login_pw;
    String id;
    String pw;
    static Context context;
    String social_id;
    String name;
    String email;
    String gender;
    String birthday;
    JSONObject jsonObject;
    private String loginurl = "3.17.67.156:5000/login";
    URL url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homeactivity = (Activity) HomeActivity.this;
        init();
        init_View();
        login_id = (EditText) findViewById(R.id.login_id);
        login_pw = (EditText) findViewById(R.id.login_pw);
        findViewById(R.id.loginbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = login_id.getText().toString();
                pw = login_pw.getText().toString();
                if (id.length() == 0) {
                    Toast.makeText(getApplicationContext(), "아이디를 입력해 주세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pw.length() == 0) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력해 주세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                jsonObject = new JSONObject();
                try {
                    jsonObject.accumulate("ID", id);
                    jsonObject.accumulate("PW", pw);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                new Thread() {
                    @Override
                    public void run() {
                        HttpURLConnection con = null;
                        try {
                            url = new URL("http://3.17.67.156:5000/login");
                            String output = jsonObject.toString();
                            con = (HttpURLConnection) url.openConnection();
                            con.setConnectTimeout(15 * 1000);
                            con.setReadTimeout(15 * 1000);
                            con.setRequestMethod("POST");
                            con.setDoInput(true);
                            con.setDoOutput(true);
                            con.setRequestProperty("Cache-Control", "no-cache");
                            con.setRequestProperty("Accept-Charset", "EUC-KR");
                            con.setRequestProperty("Content-Type", "application/json");
                            con.setRequestProperty("Accept", "application/json");
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
                            PrintWriter os = new PrintWriter(outputStreamWriter);
                            os.write(output);
                            os.flush();
                            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                            String buf;
                            buf = br.readLine();
                            Intent intent = new Intent();
                            intent.putExtra("name", "손수영");
                            intent.putExtra("email", "poew1114@naver.com");
                            if(buf.matches("\"yes\"")) {
                                setResult(RESULT_OK,intent);
                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                finish();
                            } else if(buf.matches("\"no\"")) {
                                setResult(RESULT_CANCELED);
                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                finish();
                            }
                        } catch (IOException e) {
                            setResult(RESULT_CANCELED);
                            e.printStackTrace();
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            finish();
                        }
                    }
                }.start();
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
        if (intent.getBooleanExtra("need", true)) {
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
                                social_id = result.getJSONObject("response").getString("id");
                                name = result.getJSONObject("response").getString("name");
                                email = result.getJSONObject("response").getString("email");
                                gender = result.getJSONObject("response").getString("gender");
                                birthday = result.getJSONObject("response").getString("birthday");
                                Intent regintent = new Intent(HomeActivity.this, RegisterSocialActivity.class);
                                regintent.putExtra("name", name);
                                regintent.putExtra("email", email);
                                regintent.putExtra("gender", gender);
                                regintent.putExtra("birthday", birthday);
                                startActivityForResult(regintent, REQUEST_SOCIAL_REGISTER);
                                if (social_id == null) {
                                    Intent intent = new Intent();
                                    intent.putExtra("name", name);
                                    intent.putExtra("email", email);
                                    setResult(RESULT_OK, intent);
                                    finish();
                                }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SOCIAL_REGISTER) {
            if (resultCode == RESULT_OK) {
                Intent intent = new Intent();
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }
}
