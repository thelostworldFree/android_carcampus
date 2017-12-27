package com.ljheee.CarCampus.mainone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ljheee.CarCampus.R;

public class MainActivity extends AppCompatActivity {
    private TextView start,end;
    private Button change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        start = (TextView)this.findViewById(R.id.start);
        end = (TextView)this.findViewById(R.id.end);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,LocationActivity.class);
                startActivityForResult(i,0);
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,LocationActivity.class);
                startActivityForResult(i,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0)
            start.setText(data.getStringExtra("Location"));
        else if (requestCode == 1)
            end.setText(data.getStringExtra("Location"));
    }

    protected void changeLocation(View view) {
        final String[] text = new String[1];
        change = (Button)this.findViewById(R.id.change_button);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text[0] = start.getText().toString();
                start.setText(end.getText());
                end.setText(text[0]);
            }
        });
    }
}
