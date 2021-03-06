package com.wangyukui.ywkj.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangyukui.R;
import com.wangyukui.ywkj.bean.EditResumeDataBean;
import com.wangyukui.ywkj.bean.LangBean;
import com.wangyukui.ywkj.jmessage.JGApplication;

import java.util.List;

/**
 * Created by Administrator on 2017/5/2.
 */

public class MyListLangAdapter extends DefualtAdapter {
    private List<EditResumeDataBean.DataBean.ResumeLangBean> datas;
    public MyListLangAdapter(List<EditResumeDataBean.DataBean.ResumeLangBean> datas) {
        super(datas);
       this.datas=datas;
    }

    public void setData(List<EditResumeDataBean.DataBean.ResumeLangBean> data){
        datas.clear();
        datas.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder=null;
        String degree=datas.get(position).getDegree()+"";
        if (convertView==null){
            holder=new MyViewHolder();
            convertView=View.inflate(JGApplication.context, R.layout.list_view_work_exper,null);
            holder.textcompanyname= (TextView) convertView.findViewById(R.id.textcompanyname);
            holder.texttime= (TextView) convertView.findViewById(R.id.texttime);
            holder.texttimeend= (TextView) convertView.findViewById(R.id.texttimeend);
            holder.textworkname= (TextView) convertView.findViewById(R.id.textworkname);
            holder.textworkarea= (TextView) convertView.findViewById(R.id.textworkarea);
            holder.textzhiyu= (TextView) convertView.findViewById(R.id.textzhiyu);


            convertView.setTag(holder);
        }else{
            holder= (MyViewHolder) convertView.getTag();
        }

        holder.texttime.setVisibility(View.GONE);
        holder.texttimeend.setVisibility(View.GONE);
        holder.textzhiyu.setVisibility(View.GONE);
        holder.textworkname.setText(datas.get(position).getLanguage_name());
        holder.textcompanyname.setText(datas.get(position).getDegree()+"");
        holder.textworkarea.setText(datas.get(position).getLevel_name());

        if (degree.equals("1")){
            holder.textcompanyname.setText("入门");
        }else if(degree.equals("2")){
            holder.textcompanyname.setText("熟练");
        }else if(degree.equals("3")){
            holder.textcompanyname.setText("精通");
        }

        return convertView;
    }

    class MyViewHolder{
        private TextView texttime, texttimeend, textworkname, textcompanyname, textworkarea,textzhiyu;

    }
}
