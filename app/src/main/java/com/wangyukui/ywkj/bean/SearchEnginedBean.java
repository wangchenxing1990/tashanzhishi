package com.wangyukui.ywkj.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/11/30.
 */

public class SearchEnginedBean implements Serializable {
    /**
     * current_page : 1
     * data : [{"id":"101","search_name":".nxnznxi","updated_at":"2017-08-22 15:30:09"},{"id":"99","search_name":"women","updated_at":"2017-07-18 10:29:23"}]
     * first_page_url : http://192.168.1.115:1024/api/v1/search_engine?page=1
     * from : 1
     * next_page_url : null
     * path : http://192.168.1.115:1024/api/v1/search_engine
     * per_page : 15
     * prev_page_url : null
     * to : 2
     * code : 1
     * msg :
     */

    private int current_page;
    private String first_page_url;
    private int from;
    private String next_page_url;
    private String path;
    private int per_page;
    private String prev_page_url;
    private int to;
    private String code;
    private String msg;
    private List<DataBean> data;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public String getFirst_page_url() {
        return first_page_url;
    }

    public void setFirst_page_url(String first_page_url) {
        this.first_page_url = first_page_url;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public String getNext_page_url() {
        return next_page_url;
    }

    public void setNext_page_url(String next_page_url) {
        this.next_page_url = next_page_url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public String getPrev_page_url() {
        return prev_page_url;
    }

    public void setPrev_page_url(String prev_page_url) {
        this.prev_page_url = prev_page_url;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

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
         * id : 101
         * search_name : .nxnznxi
         * updated_at : 2017-08-22 15:30:09
         */

        private String id;
        private String search_name;
        private String updated_at;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSearch_name() {
            return search_name;
        }

        public void setSearch_name(String search_name) {
            this.search_name = search_name;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }
}
