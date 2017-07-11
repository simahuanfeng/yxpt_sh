package com.bbld.yxpt_sh.bean;

/**
 * 登录
 * Created by dell on 2017/7/1.
 */

public class Login {
    /** "status": 0,
     "mes": "登录成功",
     "token": "2c2b5e1244a74a75ac5fb143f4dd8613",
     "shopname": "企赢动力的小店",
     "shopimg":       "http://img.zcool.cn/community/01bf1655e514b16ac7251df840273f.jpg"
     **/
    private int status;
    private String   mes;
    private String token;
    private String shopname;
    private String shopimg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getShopimg() {
        return shopimg;
    }

    public void setShopimg(String shopimg) {
        this.shopimg = shopimg;
    }
}
