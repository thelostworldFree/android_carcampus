package com.ljheee.CarCampus.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ljheee.CarCampus.Information;
import com.ljheee.CarCampus.R;
import com.ljheee.CarCampus.Setup;
import com.ljheee.CarCampus.mainone.AboutActivity;
import com.ljheee.CarCampus.mainone.BadgeView;
import com.ljheee.CarCampus.mainone.CollectionActivity;
import com.ljheee.CarCampus.mainone.GoutActivity;
import com.ljheee.CarCampus.mainone.OrderActivity;
import com.ljheee.CarCampus.mainone.RemindActivity;

@SuppressLint("NewApi")
public class SearchFragment extends Fragment {
    RelativeLayout view_user, btn_set, my_order, collection, remind, about,gout;
    BadgeView badgeView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, null);

        return  view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        badgeView = new BadgeView(getContext());
        badgeView.setTargetView(getActivity().findViewById(R.id.my_order));
        badgeView.setBadgeGravity(Gravity.RIGHT | Gravity.CENTER);
        badgeView.setBadgeMargin(0, 0, 10, 0);
        badgeView.setText("NEW");


        view_user = (RelativeLayout)getActivity().findViewById(R.id.view_user);
        view_user.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent a = new Intent();
                //SoilsenerActivity.class为想要跳转的Activity
                 a.setClass(getActivity(), Information.class);
                startActivity(a);
            }
        });
        btn_set = (RelativeLayout)getActivity().findViewById(R.id.btn_set);
        btn_set.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent a = new Intent();
                //SoilsenerActivity.class为想要跳转的Activity
                a.setClass(getActivity(), Setup.class);
                startActivity(a);
            }
        });
        my_order = (RelativeLayout)getActivity().findViewById(R.id.my_order);
        my_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), OrderActivity.class);
                startActivity(intent);
            }
        });
        collection = (RelativeLayout)getActivity().findViewById(R.id.collection);
        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), CollectionActivity.class);
                startActivity(intent);
            }
        });

        gout = (RelativeLayout)getActivity().findViewById(R.id.gout);
        gout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), GoutActivity.class);
                startActivity(intent);
            }
        });

        remind = (RelativeLayout)getActivity().findViewById(R.id.remind);
        remind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), RemindActivity.class);
                startActivity(intent);
            }
        });
        about = (RelativeLayout)getActivity().findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), AboutActivity.class);
                startActivity(intent);
            }
        });
    }
}