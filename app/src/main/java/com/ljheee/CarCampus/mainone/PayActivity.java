package com.ljheee.CarCampus.mainone;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ljheee.CarCampus.R;

import java.util.List;

public class PayActivity extends AppCompatActivity {
    private RelativeLayout rl_bu;
    String startLocation, endLocation, startDate, startTime, dues;
    TextView path, price, time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        rl_bu = (RelativeLayout)findViewById(R.id.rl_bu);

        DatabaseHelper database = new DatabaseHelper(this);//这段代码放到Activity类中才用this
        SQLiteDatabase db = database.getWritableDatabase();

        Cursor c = db.query("mytable",null,null,null,null,null,null);//查询并获得游标
        if(c.moveToFirst()) {//判断游标是否为空
            c.moveToLast();//移动到最近记录
            startLocation = c.getString(c.getColumnIndex("startLocation"));
            endLocation = c.getString(c.getColumnIndex("endLocation"));
            startDate = c.getString(c.getColumnIndex("startDate"));
            startTime = c.getString(c.getColumnIndex("startTime"));
            dues = c.getString(c.getColumnIndex("dues"));
        }
        c.close();

        path = (TextView) findViewById(R.id.path);
        time = (TextView) findViewById(R.id.time);
        price = (TextView) findViewById(R.id.price);

        path.setText(startLocation + " → " + endLocation);
        time.setText(startDate + " " + startTime);
        price.setText(dues);
    }



    //微信支付
    public void payByWei(View v){
        openApplication("com.tencent.mm");
    }

    //支付宝支付
    public void payByZhi(View v){
        openApplication("com.eg.android.AlipayGphone");
    }








    public void openApplication(String packgName){
        PackageInfo packageInfo;
        //String packgName = "com.tencent.mm";//微信
        //com.eg.android.AlipayGphone 支付宝包名
        try{
            packageInfo = PayActivity.this.getPackageManager().getPackageInfo(packgName,0);
            Intent resolveIntent = new Intent(Intent.ACTION_MAIN,null);
            resolveIntent.setPackage(packageInfo.packageName);
            PackageManager packageManager = PayActivity.this.getPackageManager();
            List<ResolveInfo> apps = packageManager.queryIntentActivities(resolveIntent,0);
            ResolveInfo ri = apps.iterator().next();
            if(ri!=null){
                packgName = ri.activityInfo.packageName;
                String className = ri.activityInfo.name;
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                ComponentName cn = new ComponentName(packgName,className);
                intent.setComponent(cn);
                PayActivity.this.startActivity(intent);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
