package com.wangyukui.ywkj.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/11/29.
 */

public class LookMeDataBean implements Serializable {
    /**
     * current_page : 1
     * data : [{"com_id":26,"ecom_id":"f8e3c726","company_name":"一网网络科技有限公司","time":"2017-11-28","industry_name":"计算机业（软件、数据库、系统集成）","region_name":"椒江区"},{"com_id":8,"ecom_id":"119fb58","company_name":"台州张雨荣公司","time":"2017-11-22","industry_name":"电子、微电子技术","region_name":"椒江区"},{"com_id":121,"ecom_id":"7fffa0121","company_name":"h1602122235","time":"2017-11-22","industry_name":"计算机业（软件、数据库、系统集成）","region_name":"路桥区"},{"com_id":131,"ecom_id":"e681fa131","company_name":"123456","time":"2017-11-16","industry_name":"家具、家电、工艺品、玩具","region_name":"仙居县"},{"com_id":8,"ecom_id":"119fb58","company_name":"台州张雨荣公司","time":"2017-11-16","industry_name":"电子、微电子技术","region_name":"椒江区"},{"com_id":145,"ecom_id":"b22ca6145","company_name":"1231231231","time":"2017-11-16","industry_name":"互联网、电子商务","region_name":"椒江区"},{"com_id":26,"ecom_id":"f8e3c726","company_name":"一网网络科技有限公司","time":"2017-11-16","industry_name":"计算机业（软件、数据库、系统集成）","region_name":"椒江区"},{"com_id":121,"ecom_id":"7fffa0121","company_name":"h1602122235","time":"2017-11-15","industry_name":"计算机业（软件、数据库、系统集成）","region_name":"路桥区"},{"com_id":132,"ecom_id":"f2c285132","company_name":"江健力大公司","time":"2017-11-13","industry_name":"通讯、电信网络设备业","region_name":"椒江区"},{"com_id":102,"ecom_id":"44787f102","company_name":"Bing234","time":"2017-11-13","industry_name":"批发零售(百货、超市、购物中心、专卖店\u2026","region_name":"椒江区"},{"com_id":145,"ecom_id":"b22ca6145","company_name":"1231231231","time":"2017-11-11","industry_name":"互联网、电子商务","region_name":"椒江区"},{"com_id":8,"ecom_id":"119fb58","company_name":"台州张雨荣公司","time":"2017-11-10","industry_name":"电子、微电子技术","region_name":"椒江区"},{"com_id":121,"ecom_id":"7fffa0121","company_name":"h1602122235","time":"2017-11-09","industry_name":"计算机业（软件、数据库、系统集成）","region_name":"路桥区"},{"com_id":124,"ecom_id":"b99905124","company_name":"保险公司","time":"2017-11-09","industry_name":"金融业（投资、保险、证券、银行、基金）","region_name":"颍州区"},{"com_id":144,"ecom_id":"aea0d0144","company_name":"果色生香","time":"2017-11-09","industry_name":"计算机业（软件、数据库、系统集成）","region_name":"岳麓区"}]
     * first_page_url : http://192.168.1.115:1024/api/v1/user/browse/com?page=1
     * from : 1
     * next_page_url : http://192.168.1.115:1024/api/v1/user/browse/com?page=2
     * path : http://192.168.1.115:1024/api/v1/user/browse/com
     * per_page : 15
     * prev_page_url : null
     * to : 15
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
         * com_id : 26
         * ecom_id : f8e3c726
         * company_name : 一网网络科技有限公司
         * time : 2017-11-28
         * industry_name : 计算机业（软件、数据库、系统集成）
         * region_name : 椒江区
         */

        private int com_id;
        private String ecom_id;
        private String company_name;
        private String time;
        private String industry_name;
        private String region_name;

        public int getCom_id() {
            return com_id;
        }

        public void setCom_id(int com_id) {
            this.com_id = com_id;
        }

        public String getEcom_id() {
            return ecom_id;
        }

        public void setEcom_id(String ecom_id) {
            this.ecom_id = ecom_id;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getIndustry_name() {
            return industry_name;
        }

        public void setIndustry_name(String industry_name) {
            this.industry_name = industry_name;
        }

        public String getRegion_name() {
            return region_name;
        }

        public void setRegion_name(String region_name) {
            this.region_name = region_name;
        }
    }
}
