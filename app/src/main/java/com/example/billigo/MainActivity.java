package com.example.billigo;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity {
    private boolean addrRegist = false;
    private int REQ_LOGIN = 1;
    private int REQ_LOGOUT = 2;
    ViewPager pager;
    DrawerLayout drawerLayout;
    NavigationView nv;
    View header;
    ImageButton backButton;
    String flag;
    View content_main;
    TextView textView;
    MenuItem item;
    private int isFirst; //  초기화면인지 파악

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = (ViewPager) findViewById(R.id.pager);
        CustomAdaptor adaptor = new CustomAdaptor(getLayoutInflater());
        pager.setAdapter(adaptor);
        initLayout();
        findViewById(R.id.slidemenubtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(nv);
            }
        });
    }

    private void initLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        nv = (NavigationView) findViewById(R.id.nv_main);
        header = nv.getHeaderView(0);
        backButton = (ImageButton) header.findViewById(R.id.sideback);
        content_main = (View)findViewById(R.id.main_content);
        textView = (TextView)findViewById(R.id.amc_title);
        item = nv.getMenu().getItem(0);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.login:
                        if(item.getTitle() == "로그아웃") {
                            item.setTitle("로그인");
                            textView.setText("로그인하려면 왼쪽 버튼 클릭");
                            Intent intentlogout = new Intent(MainActivity.this, HomeActivity.class);
                            startActivityForResult(intentlogout, REQ_LOGOUT);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        } else {
                            Intent intentlogin = new Intent(MainActivity.this, HomeActivity.class);
                            startActivityForResult(intentlogin, REQ_LOGIN);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        break;
                    case R.id.homepage:
                        Intent homepageintent = new Intent(Intent.ACTION_VIEW);
                        homepageintent.setData(Uri.parse("http://naver.com"));
                        try {
                            startActivity(homepageintent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
        findViewById(R.id.dron_regist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DronRegistMainActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        findViewById(R.id.dron_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addrRegist == false) {
                    addrRegist = true;
                    startActivity(new Intent(MainActivity.this, DronRequestChaddrActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                } else {
                    startActivity(new Intent(MainActivity.this, DronRequestClickRegisterActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQ_LOGIN) {
            if (resultCode == RESULT_OK) {
                item.setTitle("로그아웃");
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show();
                textView.setText("로그아웃하려면 왼쪽 버튼 클릭");
            } else if (resultCode == RESULT_CANCELED) {
                {
                    Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show();
                    textView.setText("로그인하려면 왼쪽 버튼 클릭");
                }
            }
        } else if(requestCode == REQ_LOGOUT) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(this, "로그아웃 성공", Toast.LENGTH_SHORT).show();
                textView.setText("로그인하려면 왼쪽 버튼 클릭");
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
