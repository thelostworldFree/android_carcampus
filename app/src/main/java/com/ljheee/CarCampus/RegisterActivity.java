package com.ljheee.CarCampus;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = (Button) findViewById(R.id.main_regist_btn);
        btnRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent i = new Intent(RegisterActivity.this,MainActivity.class);
                startActivityForResult(i,0);
            }
        });

        btnLogin = (Button) findViewById(R.id.main_login_btn);
        btnLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivityForResult(i,0);
            }
        });

    }

}
