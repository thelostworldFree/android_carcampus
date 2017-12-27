package com.ljheee.CarCampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Ivan on 2017/6/28.
 */

public class Setup extends AppCompatActivity {
    RelativeLayout log_off1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_up);

        log_off1 = (RelativeLayout)findViewById(R.id.log_off1);
        log_off1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent a = new Intent();
                //SoilsenerActivity.class为想要跳转的Activity
                a.setClass(Setup.this, RegisterActivity.class);
                a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(a);
            }
        });
    }
}
