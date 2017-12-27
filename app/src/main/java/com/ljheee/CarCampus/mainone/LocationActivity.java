package com.ljheee.CarCampus.mainone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ljheee.CarCampus.MainActivity;
import com.ljheee.CarCampus.R;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this, mDatas);
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        myAdapter.setOnItemClickListener(new MyAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                    Intent i = new Intent(LocationActivity.this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.putExtra("Location", mDatas.get(position));
                    setResult(0, i);  // 0表示成功
                    finish();
            }
        });
    }
    protected void initData() {
        mDatas = new ArrayList<String>();
        mDatas.add("安仁");
        mDatas.add("白果");
        mDatas.add("成都东站");
        mDatas.add("德阳");
        mDatas.add("都江堰");
        mDatas.add("广汉");
        mDatas.add("淮口");
        mDatas.add("简阳");
        mDatas.add("乐山");
        mDatas.add("龙潭寺");
        mDatas.add("眉山");
        mDatas.add("绵阳");
        mDatas.add("内江");
        mDatas.add("南充");
        mDatas.add("郫县");
        mDatas.add("青白江");
        mDatas.add("双流");
        mDatas.add("五块石");
        mDatas.add("温江");
        mDatas.add("新津");
        mDatas.add("新都");
        mDatas.add("雅安");
        mDatas.add("昭觉寺");
        mDatas.add("资阳");
    }
}
