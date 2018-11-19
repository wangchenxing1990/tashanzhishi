package com.wangyukui.ywkj.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/11/29.
 */

public class InterVewDataBean implements Serializable {
    /**
     * per_page : 15
     * current_page : 1
     * next_page_url : null
     * prev_page_url : null
     * from : 1
     * to : 5
     * data : [{"id":359675,"job_id":"197a37177219","interview_time":"2017-11-18 10:00","status":1,"remark":"","contacts":"徐女士","mobile":"","phone":"0576-89012951","company_name":"台州一鼎知识产权代理有限公司","job_title":"创业合伙人","address":"台州市椒江洪家星星电子商务产业园区A3幢2楼205室","lines":"","time":"2017-11-22","salary":"1万-100万"},{"id":359452,"job_id":"3c0ae8179144","interview_time":"2017-11-14 10:00","status":2,"remark":"","contacts":"张先生","mobile":"18967677251","phone":"0576-82441111","company_name":"台州一网科技有限公司","job_title":"网页美工ui","address":"经济开发区鑫泰广场419室","lines":"108路、915路市府大楼下车；123路;902路外环市民广场下车","time":"2017-11-13","salary":"6000-1.5万"},{"id":352442,"job_id":"3400d1179146","interview_time":"2017-07-23 08:39","status":1,"remark":"","contacts":"张先生","mobile":"18967677251","phone":"0576-82441111","company_name":"台州一网科技有限公司","job_title":"安卓开发/android开发","address":"经济开发区鑫泰广场419室","lines":"108路、915路市府大楼下车；123路;902路外环市民广场下车","time":"2017-07-19","salary":"6000-1.5万"},{"id":352415,"job_id":"1fb692195493","interview_time":"2017-07-21 16:00","status":1,"remark":"来了解了解","contacts":"张先生","mobile":"18967677251","phone":"0576-88532337","company_name":"台州一网科技有限公司","job_title":"产品经理","address":"经济开发区鑫泰广场419室","lines":"108路、915路市府大楼下车；123路;902路外环市民广场下车","time":"2017-07-18","salary":"6000-1.5万"},{"id":352408,"job_id":"717b98182551","interview_time":"2017-07-20 17:39","status":1,"remark":"","contacts":"张先生","mobile":"18967677251","phone":"0576-82441111","company_name":"台州一网科技有限公司","job_title":"高级php程序员","address":"经济开发区鑫泰广场419室","lines":"108路、915路市府大楼下车；123路;902路外环市民广场下车","time":"2017-07-18","salary":"7000-1.5万"}]
     * code : 1
     * msg :
     */

    private int per_page;
    private int current_page;
    private String next_page_url;
    private String prev_page_url;
    private int from;
    private int to;
    private String code;
    private String msg;
    private List<DataBean> data;

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public String getNext_page_url() {
        return next_page_url;
    }

    public void setNext_page_url(String next_page_url) {
        this.next_page_url = next_page_url;
    }

    public String getPrev_page_url() {
        return prev_page_url;
    }

    public void setPrev_page_url(String prev_page_url) {
        this.prev_page_url = prev_page_url;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
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
         * id : 359675
         * job_id : 197a37177219
         * interview_time : 2017-11-18 10:00
         * status : 1
         * remark :
         * contacts : 徐女士
         * mobile :
         * phone : 0576-89012951
         * company_name : 台州一鼎知识产权代理有限公司
         * job_title : 创业合伙人
         * address : 台州市椒江洪家星星电子商务产业园区A3幢2楼205室
         * lines :
         * time : 2017-11-22
         * salary : 1万-100万
         */

        private String id;
        private String job_id;
        private String interview_time;
        private int status;
        private String remark;
        private String contacts;
        private String mobile;
        private String phone;
        private String company_name;
        private String job_title;
        private String address;
        private String lines;
        private String time;
        private String salary;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getJob_id() {
            return job_id;
        }

        public void setJob_id(String job_id) {
            this.job_id = job_id;
        }

        public String getInterview_time() {
            return interview_time;
        }

        public void setInterview_time(String interview_time) {
            this.interview_time = interview_time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getJob_title() {
            return job_title;
        }

        public void setJob_title(String job_title) {
            this.job_title = job_title;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLines() {
            return lines;
        }

        public void setLines(String lines) {
            this.lines = lines;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }
    }
}
