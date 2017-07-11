package com.bbld.yxpt_sh.bean;

/**
 * 获取商户活动信息
 * Created by dell on 2017/7/1.
 */

public class GetShopActivityInfo {
    /**
     * "status": 0,
     "mes": "操作成功",
     "ActivityInfo": {}
     */
    private int status;
    private String mes;
    private GetShopActivityInfoRes ActivityInfo;

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

    public GetShopActivityInfoRes getActivityInfo() {
        return ActivityInfo;
    }

    public void setActivityInfo(GetShopActivityInfoRes activityInfo) {
        ActivityInfo = activityInfo;
    }

    public static class  GetShopActivityInfoRes{
        /**
         "NOID": 8,
         "Title": "啊啊啊啊啊1",
         "Discount": 80,
         "Remark": "123123123"
         */
        private int NOID;
        private String Title;
        private String Discount;
        private String Remark;

        public int getNOID() {
            return NOID;
        }

        public void setNOID(int NOID) {
            this.NOID = NOID;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getDiscount() {
            return Discount;
        }

        public void setDiscount(String discount) {
            Discount = discount;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String remark) {
            Remark = remark;
        }
    }
}
