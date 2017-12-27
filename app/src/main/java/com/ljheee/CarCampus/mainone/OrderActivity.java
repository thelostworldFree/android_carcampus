package com.ljheee.CarCampus.mainone;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ljheee.CarCampus.R;

import static android.content.DialogInterface.OnClickListener;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String startLocation, endLocation, startDate, startTime, dues;
        boolean isLast = true;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
//      找到容器
        /* Called when the activity is first created. */
        LinearLayout mLinearLayout = (LinearLayout) findViewById(R.id.box);

        DatabaseHelper database = new DatabaseHelper(this);//这段代码放到Activity类中才用this
        SQLiteDatabase db = database.getWritableDatabase();

        Cursor c = db.query("mytable",null,null,null,null,null,null);//查询并获得游标
        if(c.moveToFirst()){//判断游标是否为空
            for(int i = c.getCount() - 1; i >= 0; i--){
                if (c.isLast())
                    isLast = false;

                c.moveToPosition(i);//移动到指定记录
                startLocation = c.getString(c.getColumnIndex("startLocation"));
                endLocation = c.getString(c.getColumnIndex("endLocation"));
                startDate = c.getString(c.getColumnIndex("startDate"));
                startTime = c.getString(c.getColumnIndex("startTime"));
                dues = c.getString(c.getColumnIndex("dues"));

//              实例化一个子View
                MenuLandscapeLinearLayout mMenuLandscapeLinearLayout = new MenuLandscapeLinearLayout(this, startLocation, endLocation, startDate, startTime, dues, isLast);
//              添加到容器
                mLinearLayout.addView(mMenuLandscapeLinearLayout);
            }
        }
        c.close();
        db.close();
    }

    public class MenuLandscapeLinearLayout extends LinearLayout {
        //  构造函数
        @SuppressLint("SetTextI18n")
        public MenuLandscapeLinearLayout(Context context, String startLocation, String endLocation, String startDate, String startTime, String dues, boolean isLast) {
            super(context);
            // TODO Auto-generated constructor stub
            //加载需要的属性，加载方法一的子Layout
            ((Activity) getContext()).getLayoutInflater().inflate(R.layout.order, this);
            //在此你可以封装很多方法
            if (isLast) {
                ImageView newOrder = (ImageView) findViewById(R.id.new_order);
                newOrder.setImageResource(R.drawable.new_order);
                ImageView refundButton = findViewById(R.id.refund_button);
                refundButton.setImageResource(R.drawable.delete_order);
            }

            TextView path = (TextView) findViewById(R.id.path1);
            TextView time = (TextView) findViewById(R.id.start_time1);
            TextView price = (TextView) findViewById(R.id.price1);

            path.setText(startLocation + " → " + endLocation);
            time.setText(startDate + " " + startTime);
            price.setText(dues);
        }
    }

    public void refund(View view) {
        // 通过Dialog提示是否删除
        AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
        builder.setMessage("确定要退票吗？");

        // 确定按钮点击事件
        builder.setPositiveButton("确定", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHelper database = new DatabaseHelper(OrderActivity.this);//这段代码放到Activity类中才用this
                SQLiteDatabase db = database.getWritableDatabase();

                @SuppressLint("Recycle") Cursor c = db.rawQuery("select * from mytable limit 1 offset (select count(*) - 1  from mytable)",null);//查询并获得游标
                if (c.moveToFirst()) {
                    String sql = "delete from mytable where _id = " + c.getString(c.getColumnIndex("_id"));
                    db.execSQL(sql);
                }

                c.close();
                db.close();
                Intent intent = new Intent(OrderActivity.this, OrderActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 取消按钮点击事件
        builder.setNegativeButton("取消", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
}
