package com.wangyukui.ywkj.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/10/18.
 */

public class LocationAreaBean implements Serializable {


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

    public static class DataBean implements Serializable{

        private String cid;
        private String name;
        private int grade;
        private int next;
        private List<NextsBeanX> nexts;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public int getNext() {
            return next;
        }

        public void setNext(int next) {
            this.next = next;
        }

        public List<NextsBeanX> getNexts() {
            return nexts;
        }

        public void setNexts(List<NextsBeanX> nexts) {
            this.nexts = nexts;
        }

        public static class NextsBeanX implements Serializable{
            /**
             * cid : 130100
             * name : 石家庄市
             * grade : 2
             * next : 1
             * nexts : [{"cid":130102,"name":"长安区","grade":3,"next":0},{"cid":130104,"name":"桥西区","grade":3,"next":0},{"cid":130105,"name":"新华区","grade":3,"next":0},{"cid":130107,"name":"井陉矿区","grade":3,"next":0},{"cid":130108,"name":"裕华区","grade":3,"next":0},{"cid":130109,"name":"藁城区","grade":3,"next":0},{"cid":130110,"name":"鹿泉区","grade":3,"next":0},{"cid":130111,"name":"栾城区","grade":3,"next":0},{"cid":130121,"name":"井陉县","grade":3,"next":0},{"cid":130123,"name":"正定县","grade":3,"next":0},{"cid":130125,"name":"行唐县","grade":3,"next":0},{"cid":130126,"name":"灵寿县","grade":3,"next":0},{"cid":130127,"name":"高邑县","grade":3,"next":0},{"cid":130128,"name":"深泽县","grade":3,"next":0},{"cid":130129,"name":"赞皇县","grade":3,"next":0},{"cid":130130,"name":"无极县","grade":3,"next":0},{"cid":130131,"name":"平山县","grade":3,"next":0},{"cid":130132,"name":"元氏县","grade":3,"next":0},{"cid":130133,"name":"赵县","grade":3,"next":0},{"cid":130181,"name":"辛集市","grade":3,"next":0},{"cid":130183,"name":"晋州市","grade":3,"next":0},{"cid":130184,"name":"新乐市","grade":3,"next":0}]
             */

            private String cid;
            private String name;
            private int grade;
            private int next;
            private List<NextsBean> nexts;

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getGrade() {
                return grade;
            }

            public void setGrade(int grade) {
                this.grade = grade;
            }

            public int getNext() {
                return next;
            }

            public void setNext(int next) {
                this.next = next;
            }

            public List<NextsBean> getNexts() {
                return nexts;
            }

            public void setNexts(List<NextsBean> nexts) {
                this.nexts = nexts;
            }

            public static class NextsBean implements Serializable{
                /**
                 * cid : 130102
                 * name : 长安区
                 * grade : 3
                 * next : 0
                 */

                private String cid;
                private String name;
                private int grade;
                private int next;

                public String getCid() {
                    return cid;
                }

                public void setCid(String cid) {
                    this.cid = cid;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getGrade() {
                    return grade;
                }

                public void setGrade(int grade) {
                    this.grade = grade;
                }

                public int getNext() {
                    return next;
                }

                public void setNext(int next) {
                    this.next = next;
                }
            }
        }
    }
}
