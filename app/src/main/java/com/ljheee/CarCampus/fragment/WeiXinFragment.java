package com.ljheee.CarCampus.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ljheee.CarCampus.R;
import com.ljheee.CarCampus.mainone.BookingActivity;
import com.ljheee.CarCampus.mainone.LocationActivity;
import com.ljheee.CarCampus.mainone.TimePickerDialog;

//加载页面
public class WeiXinFragment extends Fragment {
    View mView;
    private TextView start,end;
    private ImageView change;
    private TextView tv_data= null;
    private TextView tv_data1= null;
    private TextView booking_button;
    private TextView tv_button;
    private com.ljheee.CarCampus.mainone.TimePickerDialog mytimePickerDialog;
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_main,null);

        }
        //((ListView)mView.findViewById(R.id.lv)).setAdapter(adapter);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        start = ((TextView)mView.findViewById(R.id.start));
        end = (TextView)mView.findViewById(R.id.end);
        change = (ImageView)mView.findViewById(R.id.change_button);
        tv_button = (TextView)mView.findViewById(R.id.tv_button);
        tv_data = (TextView)mView.findViewById(R.id.tv_data);
        tv_data1= (TextView)mView.findViewById(R.id.tv_data1);
        booking_button = (TextView)mView.findViewById(R.id.booking_button);

        //第一个选取的地址
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),LocationActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(i,0);
            }
        });
        //第二个选取的地址
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),LocationActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(i,1);
            }
        });
        //交换地址
            change.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String[] text = new String[1];
                    text[0] = start.getText().toString();
                    start.setText(end.getText());
                    end.setText(text[0]);
                }
            });

        tv_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mytimePickerDialog = new TimePickerDialog(getContext());
                mytimePickerDialog.showDateAndTimePickerDialog(tv_data,tv_data1);

            }
        });
        booking_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if(start.getText().equals("") || end.getText().equals("")){
                        Toast.makeText(getContext(),"请选择车程",Toast.LENGTH_SHORT).show();
                    }else if(tv_data.getText().equals("") ||tv_data.getText().equals("")){
                        Toast.makeText(getContext(),"请选择日期或者时间",Toast.LENGTH_SHORT).show();
                    }else{
                        Intent i = new Intent(getActivity(), BookingActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("StartLocation", start.getText().toString());
                        bundle.putString("EndLocation", end.getText().toString());
                        bundle.putString("TextLocation", tv_data.getText().toString());
                        bundle.putString("TextLocation2", tv_data1.getText().toString());
                        i.putExtras(bundle);
                        startActivity(i);
                    }

                    //Toast.makeText(getContext(),"您还没有选定好上车信息！",Toast.LENGTH_LONG).show();

            }
        });


    }

//获取地址的文本
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            if(start != null && data !=null)//如果没有点击ite就返回到mainactivity的界面(获取的值就没有为null)
                start.setText(data.getStringExtra("Location"));
        }
        else if (requestCode == 1) {
            if(end != null && data != null)
            end.setText(data.getStringExtra("Location"));
        }
    }


}
