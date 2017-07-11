package com.bbld.yxpt_sh.bean;

/**
 * 锁定/解锁商户活动信息
 * Created by dell on 2017/7/1.
 */

public class LockShopActivity {
    /**
     * "status": 0,
     "mes": "操作成功",
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
