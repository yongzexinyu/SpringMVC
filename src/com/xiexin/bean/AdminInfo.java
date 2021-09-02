package com.xiexin.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AdminInfo {


    private  String adminName;

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }


    @Override
    public String toString() {
        return "AdminInfo{" +
                "adminName='" + adminName + '\'' +
                ", adminPwd='" + adminPwd + '\'' +
                ", adminTime=" + adminTime +
                ", loverList=" + loverList +
                ", aiHao=" + Arrays.toString(aiHao) +
                '}';
    }

    public Date getAdminTime() {
        return adminTime;
    }

    public void setAdminTime(Date adminTime) {
        this.adminTime = adminTime;
    }

    private  String adminPwd;

    public List<Lover> getLoverList() {
        return loverList;
    }

    public Integer[] getAiHao() {
        return aiHao;
    }

    public void setAiHao(Integer[] aiHao) {
        this.aiHao = aiHao;
    }

    public void setLoverList(List<Lover> loverList) {
        this.loverList = loverList;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date adminTime;
    private List<Lover> loverList;
    private  Integer[] aiHao;//1.写代码 2.看书 3.读报
}
