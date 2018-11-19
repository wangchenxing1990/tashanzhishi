package com.wangyukui.ywkj.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/11/28.
 */

public class SubmitResumeDataBean implements Serializable {

    /**
     * current_page : 1
     * data : [{"id":416,"job_id":"488e7e207","status":0,"created_at":"2017-11-08 15:52:18","job_title":"123","company_name":"h1602122235"},{"id":399,"job_id":"488e7e207","status":0,"created_at":"2017-09-26 16:28:23","job_title":"123","company_name":"h1602122235"},{"id":396,"job_id":"f1cc3a169","status":0,"created_at":"2017-09-26 16:18:03","job_title":"8888","company_name":"过家家"},{"id":395,"job_id":"9b8735160","status":0,"created_at":"2017-09-26 16:16:41","job_title":"Phpkaifa","company_name":"Bing234"},{"id":393,"job_id":"c9dfca191","status":0,"created_at":"2017-09-21 08:56:54","job_title":"文员","company_name":"江苏兴昌人力资源服务股份有限公司"},{"id":392,"job_id":"13a33b185","status":0,"created_at":"2017-09-21 08:56:07","job_title":"江健力职位2","company_name":"江健力大公司"},{"id":391,"job_id":"f8f22d165","status":2,"created_at":"2017-09-21 08:46:35","job_title":"j\u2006d\u2006j\u2006j\u2006d","company_name":"一网网络科技有限公司"},{"id":361,"job_id":"13a33b185","status":1,"created_at":"2017-08-25 14:30:12","job_title":"江健力职位2","company_name":"江健力大公司"},{"id":334,"job_id":"b2b95b162","status":1,"created_at":"2017-07-29 10:32:37","job_title":"保险公司2","company_name":"保险公司"},{"id":335,"job_id":"3a23ac167","status":1,"created_at":"2017-07-31 11:24:56","job_title":"IOS开发工程师","company_name":"一网网络科技有限公司"}]
     * first_page_url : http://192.168.1.115:1024/api/v1/resume_send?page=1
     * from : 1
     * next_page_url : null
     * path : http://192.168.1.115:1024/api/v1/resume_send
     * per_page : 15
     * prev_page_url : null
     * to : 10
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
        return  next_page_url;
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
        return  prev_page_url;
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

    public static class DataBean implements Serializable{
        /**
         * id : 416
         * job_id : 488e7e207
         * status : 0
         * created_at : 2017-11-08 15:52:18
         * job_title : 123
         * company_name : h1602122235
         */

        private int id;
        private String job_id;
        private int status;
        private String created_at;
        private String job_title;
        private String company_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getJob_id() {
            return job_id;
        }

        public void setJob_id(String job_id) {
            this.job_id = job_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getJob_title() {
            return job_title;
        }

        public void setJob_title(String job_title) {
            this.job_title = job_title;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }
    }
}
