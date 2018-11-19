package com.wangyukui.ywkj.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/2.
 */

public class SkillBean implements Serializable {
    private String skillname;
    private String degree;
    private String id;

    public SkillBean() {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSkillname() {
        return skillname;
    }

    public void setSkillname(String skillname) {
        this.skillname = skillname;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
