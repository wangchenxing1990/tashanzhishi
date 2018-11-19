package com.wangyukui.ywkj.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/11/28.
 */

public class InformationBean implements Serializable {

    /**
     * code : 1
     * msg :
     * data : [{"category":31,"name":"网站公告"},{"category":3,"name":"面试技巧"},{"category":4,"name":"事业单位"},{"category":5,"name":"职场八卦"},{"category":6,"name":"薪酬福利"},{"category":7,"name":"劳动法规"},{"category":8,"name":"职场规划"},{"category":9,"name":"培训教育"}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * category : 31
         * name : 网站公告
         */

        private int category;
        private String name;

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
