package com.bbld.yxpt_sh.bean;

/**
 * Created by dell on 2017/7/1.
 * 设置商户信息
 */

public class SetShopInfo {
    /**
     "status": 0,
     "mes": "成功",
     */
    private int status;
    private String mes;

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
}
