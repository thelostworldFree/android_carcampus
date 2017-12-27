package com.ljheee.CarCampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText et;
    private EditText et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et = (EditText) findViewById(R.id.email);
        et2 = (EditText)findViewById(R.id.password);
    }
    public void click(View view) {
        switch (view.getId()) {
            case R.id.login_button://点击按钮启动activity
                final String str = et.getText().toString().trim();
                final String str2 = et2.getText().toString().trim();
                if (str.equals(str2)&& (!str.isEmpty()&&!str2.isEmpty())){
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }
                break;
            case R.id.back_button:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;

        }
    }
}