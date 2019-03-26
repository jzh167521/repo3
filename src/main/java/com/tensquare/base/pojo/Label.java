package com.tensquare.base.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name="tb_label")
public class Label implements Serializable {
    @Id
    private String id;//主键
    private String labelname;//标签名称
    private String state;//是否可用 1表示可用 0表示不可用
    private Integer count;//表示使用的次数
    private Integer fans;//表示粉丝数
    private String recommend;//是否推荐 1表示推荐 0表示不推荐

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    @Override
    public String toString() {
        return "Label{" +
                "id='" + id + '\'' +
                ", labelname='" + labelname + '\'' +
                ", state='" + state + '\'' +
                ", count=" + count +
                ", fans=" + fans +
                ", recommend='" + recommend + '\'' +
                '}';
    }
}
