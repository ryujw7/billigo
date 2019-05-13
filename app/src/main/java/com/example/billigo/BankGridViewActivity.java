package com.example.billigo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class BankGridViewActivity extends BaseActivity {
    GridView gridView;
    int[] Buttonnames = {
            R.drawable.bank_busan, R.drawable.bank_deagu,
            R.drawable.bank_hana,  R.drawable.bank_ibk,
            R.drawable.bank_jeju,  R.drawable.bank_jeonbuk,
            R.drawable.bank_kakao,  R.drawable.bank_kb,
            R.drawable.bank_kwangju,  R.drawable.bank_kyungnam,
            R.drawable.bank_lotte,  R.drawable.bank_mirae_asset,
            R.drawable.bank_nh,  R.drawable.bank_sc,
            R.drawable.bank_seamaul,  R.drawable.bank_shinhan,
            R.drawable.bank_shinhyup,  R.drawable.bank_suhyup,
            R.drawable.bank_uchekuk,  R.drawable.bank_uri,
    };
    ImageAdaptor imageAdaptor;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview_bank);
        intent = new Intent();
        gridView = (GridView)findViewById(R.id.gridbanklist);
        imageAdaptor = new ImageAdaptor(this, Buttonnames);
        gridView.setAdapter(imageAdaptor);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0:
                        intent.putExtra("bank", "부산은행");
                        break;
                    case 1:
                        intent.putExtra("bank", "대구은행");
                        break;
                    case 2:
                        intent.putExtra("bank", "하나은행");
                        break;
                    case 3:
                        intent.putExtra("bank", "IBK기업은행");
                        break;
                    case 4:
                        intent.putExtra("bank", "제주은행");
                        break;
                    case 5:
                        intent.putExtra("bank", "전북은행");
                        break;
                    case 6:
                        intent.putExtra("bank", "카카오은행");
                        break;
                    case 7:
                        intent.putExtra("bank", "국민은행");
                        break;
                    case 8:
                        intent.putExtra("bank", "광주은행");
                        break;
                    case 9:
                        intent.putExtra("bank", "경남은행");
                        break;
                    case 10:
                        intent.putExtra("bank", "롯데은행");
                        break;
                    case 11:
                        intent.putExtra("bank", "미래에셋");
                        break;
                    case 12:
                        intent.putExtra("bank", "농협");
                        break;
                    case 13:
                        intent.putExtra("bank", "SC제일은행");
                        break;
                    case 14:
                        intent.putExtra("bank", "세마을금고");
                        break;
                    case 15:
                        intent.putExtra("bank", "신한은행");
                        break;
                    case 16:
                        intent.putExtra("bank", "신협");
                        break;
                    case 17:
                        intent.putExtra("bank", "수협");
                        break;
                    case 18:
                        intent.putExtra("bank", "우체국");
                        break;
                    case 19:
                        intent.putExtra("bank", "우리은행");
                        break;

                        default:
                            break;
                }
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
