package com.ljheee.CarCampus;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.ljheee.CarCampus.fragment.SearchFragment;
import com.ljheee.CarCampus.fragment.WeiXinFragment;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    private TextView tv1;
    // 定义FragmentTabHost对象
    private FragmentTabHost mTabHost;

    // 定义一个布局
    private LayoutInflater layoutInflater;

    // 定义数组来存放Fragment界面 CommunityFragment.class, GovermentFragment.class,
    private Class fragmentArray[] = { WeiXinFragment.class, SearchFragment.class };

    // 定义数组来存放按钮图片 R.drawable.community_main_btn, R.drawable.goverment_main_btn,
    private int mImageViewArray[] = { R.drawable.news_main_btn, R.drawable.search_main_btn };

    // Tab选项卡的文字R.string.tab_contact,R.string.tab_found,
    private int mTextviewArray[] = {R.string.tab_home, R.string.tab_me };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }



    private void initView() {
        // 实例化布局对象
        layoutInflater = LayoutInflater.from(this);

        // 实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost) findViewById(R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        // 得到fragment的个数
        int count = fragmentArray.length;

        for (int i = 0; i < count; i++) {
            // 为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(String.valueOf(mTextviewArray[i]))
                    .setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            // 设置Tab按钮的背景
            //  mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);

            //设置第一个文字的颜色getChildAt(0)关键
            TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(0).findViewById(R.id.textview);
            if (mTabHost.getCurrentTab() == 0) {//选中
                tv.setTextColor(this.getResources().getColor(R.color.nav_font_text_selected));
            } else {//不选中
                tv.setTextColor(this.getResources().getColor(R.color.nav_font_color));
            }

        }
        //让textview的字和图片同步滑动
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                // mTabHost.setCurrentTabByTag(tabId);
                upDateTab(mTabHost);
            }
        });
    }



    private void upDateTab(FragmentTabHost mTabHost) {
        for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
            TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(i).findViewById(R.id.textview);
            if (mTabHost.getCurrentTab() == i) {//选中
                tv.setTextColor(this.getResources().getColor(R.color.nav_font_text_selected));
            } else {//不选中
                tv.setTextColor(this.getResources().getColor(R.color.nav_font_color));
            }
        }


    }



    /**
     * 设置menu显示icon
     */

    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);


        return view;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_tab_host, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }



}

