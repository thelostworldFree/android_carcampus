package com.ljheee.CarCampus.mainone;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ljheee.CarCampus.R;

public class BookingActivity extends AppCompatActivity {
    TextView startLocation,endLocation,start_time,start_time2,pay_button,price_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        startLocation = (TextView)findViewById(R.id.start_location);
        endLocation = (TextView)findViewById(R.id.end_location);
        start_time = (TextView)findViewById(R.id.start_time);
        start_time2 = (TextView)findViewById(R.id.start_time2);
        pay_button = (TextView)findViewById(R.id.pay_button);
        price_view = (TextView)findViewById(R.id.price_view);
        startLocation.setText(bundle.getString("StartLocation"));
        endLocation.setText(bundle.getString("EndLocation"));
        start_time.setText(bundle.getString("TextLocation"));
        start_time2.setText(bundle.getString("TextLocation2"));

        boolean isChecked = false;
        CheckBox cbx = (CheckBox) findViewById(R.id.insurance_checkBox);
        cbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //do something
                if (isChecked) {
                    price_view.setText("￥35.00");
                } else {
                    price_view.setText("￥30.00");
                }
            }
        });
        cbx.setChecked(isChecked);

        pay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BookingActivity.this,PayActivity.class);
                /*Bundle bundle = new Bundle();
                bundle.putString("StartLocation",start.getText().toString());
                bundle.putString("EndLocation",end.getText().toString());
                bundle.putString("TextLocation",tv_data.getText().toString());
                i.putExtras(bundle);*/
                save();
                startActivity(i);
            }
        });
    }

    protected void save() {
        price_view = (TextView) this.findViewById(R.id.price_view);

        DatabaseHelper database = new DatabaseHelper(this);//这段代码放到Activity类中才用this
        SQLiteDatabase db = database.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("startLocation", startLocation.getText().toString());
        cv.put("endLocation", endLocation.getText().toString());
        cv.put("startDate", start_time.getText().toString());
        cv.put("startTime", start_time2.getText().toString());
        cv.put("dues", price_view.getText().toString());
        db.insert("mytable", null, cv);
        db.close();
    }
}
