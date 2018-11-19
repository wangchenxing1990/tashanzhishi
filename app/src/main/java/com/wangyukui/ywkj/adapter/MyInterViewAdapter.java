package com.wangyukui.ywkj.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.wangyukui.R;
import com.wangyukui.ywkj.bean.InterVewDataBean;
import com.wangyukui.ywkj.bean.InterViewBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 */

public class MyInterViewAdapter extends BaseRecyclerAdapter<MyInterViewAdapter.MyViewHolder> implements View.OnClickListener{


    private final List<InterVewDataBean.DataBean> list;
    private final Context context;
    private final String name;
    public MyInterViewAdapter(List<InterVewDataBean.DataBean> data, Context context) {
        SharedPreferences share=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        name=share.getString("name","");
        this.list=data;
        this.context=context;
    }

    public void setData(List<InterVewDataBean.DataBean> data,boolean fresh){
        if(fresh){
            list.clear();
            list.addAll(data);
        }else{
            list.addAll(data);
        }
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder getViewHolder(View view) {
        return new MyViewHolder(view,false);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_interview, parent, false);
        MyViewHolder vh = new MyViewHolder(view,true);
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position, boolean isItem) {
        holder.textjobtitle.setText(list.get(position).getJob_title());
        holder.textsalary.setText(list.get(position).getSalary()+"元/月");
        holder.textcompanyname.setText(list.get(position).getCompany_name());
        holder.textinterview_time.setText(list.get(position).getInterview_time());
        holder.textcontacts.setText(list.get(position).getContacts());
        holder.textmobile.setText(list.get(position).getMobile());
        holder.textphone.setText(list.get(position).getPhone());
        holder.textaddress.setText(list.get(position).getAddress());
        holder.textlines.setText(list.get(position).getLines());
        holder.textremark.setText(list.get(position).getRemark());
        holder.textname.setText(name+",你好，看到你的简历符合我们公司的招聘信息条件");
        if (list.get(position).getStatus()==0){
            holder.textstatus.setText("未处理");
            holder.textstatus.setBackgroundColor(0xfff67c1f);
        }else if(list.get(position).getStatus()==1){
            holder.textstatus.setText("已查看");
            holder.textstatus.setBackgroundColor(0xffe3534f);
        }else if(list.get(position).getStatus()==2){
            holder.textstatus.setText("同意邀约");
            holder.textstatus.setBackgroundColor(0xff589e46);
        }else if(list.get(position).getStatus()==3){
            holder.textstatus.setText("已被婉拒");
            holder.textstatus.setBackgroundColor(0xffcccccc);
        }else if(list.get(position).getStatus()==4){
            holder.textstatus.setText("已删除");
            holder.textstatus.setBackgroundColor(0xffe3534f);
        }


        holder.itemView.setTag(list.get(position));
    }

    @Override
    public int getAdapterItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textname,textjobtitle,
                textsalary,textcompanyname,
                textinterview_time,textcontacts,
                textmobile,textphone,textaddress,
                textlines,textremark,textstatus;

        public MyViewHolder(View itemView, boolean isItem) {
            super(itemView);
            textname= (TextView) itemView.findViewById(R.id.textname);
            textjobtitle= (TextView) itemView.findViewById(R.id.textjobtitle);
            textsalary= (TextView) itemView.findViewById(R.id.textsalary);
            textcompanyname= (TextView) itemView.findViewById(R.id.textcompanyname);
            textinterview_time= (TextView) itemView.findViewById(R.id.textinterview_time);
            textcontacts= (TextView) itemView.findViewById(R.id.textcontacts);
            textmobile= (TextView) itemView.findViewById(R.id.textmobile);
            textphone= (TextView) itemView.findViewById(R.id.textphone);
            textaddress= (TextView) itemView.findViewById(R.id.textaddress);
            textlines= (TextView) itemView.findViewById(R.id.textlines);
            textremark= (TextView) itemView.findViewById(R.id.textremark);
            textstatus= (TextView) itemView.findViewById(R.id.textstatus);

        }
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v,(InterVewDataBean.DataBean)v.getTag());
        }
    }
    public static  interface OnRecyclerViewItemClickListener{
        void onItemClick(View view,InterVewDataBean.DataBean data);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener=null;
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener){

            this.mOnItemClickListener=listener;

    }
}
