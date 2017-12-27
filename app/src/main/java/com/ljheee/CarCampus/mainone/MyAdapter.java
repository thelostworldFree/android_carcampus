package com.ljheee.CarCampus.mainone;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljheee.CarCampus.R;

import java.util.List;

/**
 * Created by YuChenghao on 2017/11/25.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<String> datas;
    public interface onItemClickListener{
        void onItemClick(View view, int position);
    }
    private onItemClickListener onItemClickListener;
    public void setOnItemClickListener(onItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    public MyAdapter(Context context, List<String> datas){
        this.context=context;
        this.datas=datas;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item, parent,
                false));
        return holder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        ViewGroup.LayoutParams layoutparams= holder.itemView.getLayoutParams();
        holder.itemView.setLayoutParams(layoutparams);
        holder.tv.setText(datas.get(position));
        if(onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPos=holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView,layoutPos);
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
        }
    }
}
