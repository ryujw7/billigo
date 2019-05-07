package com.example.billigo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DronRegistTradeAddrActivity extends BaseActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone_regist_tradeaddr);
        editText = (EditText) findViewById(R.id.editaddress);
        findViewById(R.id.adrt_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString() == "") {
                    setResult(RESULT_CANCELED);
                } else {
                    String putText = editText.getText().toString();
                    Intent intent = new Intent();
                    intent.putExtra("result",putText);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
    }
}
