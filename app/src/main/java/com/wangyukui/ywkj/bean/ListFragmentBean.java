package com.wangyukui.ywkj.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/10/27.
 */

public class ListFragmentBean implements Serializable {

    /**
     * per_page : 15
     * current_page : 1
     * next_page_url : https://www.15hr.com/api/v1/news?page=2
     * prev_page_url : null
     * from : 1
     * to : 15
     * data : [{"id":3613,"title":"台州市海洋与渔业局编外用工招聘公告","created_at":"2017-10-13","readnum":670,"img":"newsimg/201710/15078826072ms86.jpg"},{"id":3612,"title":"台州市科协关于公开选调事业单位工作人员的公告","created_at":"2017-10-13","readnum":764,"img":"newsimg/201710/15078812309wb3a.jpg"},{"id":3611,"title":"椒江公安分局区西派出所流动人口专管员招聘启事","created_at":"2017-10-13","readnum":305,"img":"newsimg/201710/1507877118nhy3r.jpg"},{"id":3610,"title":"台州市路桥同德医院招聘启事","created_at":"2017-10-13","readnum":300,"img":"newsimg/201710/15078709262ea5m.jpg"},{"id":3597,"title":"联合招聘至玉环电网工作人员的公告","created_at":"2017-10-10","readnum":281,"img":"newsimg/201710/1507600456z6l7u.jpg"},{"id":3609,"title":"椒江区人民政府法制办公室招聘编制外工作人员的通知","created_at":"2017-10-13","readnum":172,"img":"newsimg/201710/1507862546sypaw.jpg"},{"id":3608,"title":"台州市旅游质量监督管理所编外用工招聘公告","created_at":"2017-10-12","readnum":217,"img":"newsimg/201710/1507800165yyhi0.jpg"},{"id":3607,"title":"台州市椒江区爱心儿童康复基地招聘公告","created_at":"2017-10-12","readnum":173,"img":"newsimg/201710/1507776717xpljh.jpg"},{"id":3606,"title":"黄岩区委宣传部关于面向全市公开选调事业人员的通知","created_at":"2017-10-12","readnum":89,"img":"newsimg/201710/1507771376skqnw.jpg"},{"id":3605,"title":"黄岩区委宣传部面向硕士研究生公开招聘工作人员公告","created_at":"2017-10-12","readnum":122,"img":"newsimg/201710/1507771206mwl1v.jpg"},{"id":3604,"title":"临海市民卡有限公司工作人员招聘公告","created_at":"2017-10-12","readnum":215,"img":"newsimg/201710/15077709370ojod.jpg"},{"id":3603,"title":"台州市质量技术监督局招聘编制外工作人员公告","created_at":"2017-10-11","readnum":200,"img":"newsimg/201710/1507709864ulcef.jpg"},{"id":3602,"title":"温州银行股份有限公司台州分行招聘简章","created_at":"2017-10-11","readnum":1127,"img":"newsimg/201710/15076977583eexn.jpg"},{"id":3601,"title":"台州日报发行有限公司招聘启事","created_at":"2017-10-11","readnum":181,"img":"newsimg/201710/1507685626r6qfw.jpg"},{"id":3600,"title":"路桥区人武部招聘编外工作人员公告","created_at":"2017-10-11","readnum":747,"img":"newsimg/201710/1507685274shsnh.jpg"}]
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
         * id : 3613
         * title : 台州市海洋与渔业局编外用工招聘公告
         * created_at : 2017-10-13
         * readnum : 670
         * img : newsimg/201710/15078826072ms86.jpg
         */

        private int id;
        private String title;
        private String created_at;
        private int readnum;
        private String img;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getReadnum() {
            return readnum;
        }

        public void setReadnum(int readnum) {
            this.readnum = readnum;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
