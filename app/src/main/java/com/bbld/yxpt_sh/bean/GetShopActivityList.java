package com.bbld.yxpt_sh.bean;

import java.util.List;

/**
 * 商户活动列表
 * Created by dell on 2017/7/1.
 */

public class GetShopActivityList {
    /**
     "status": 0,
     "mes": "操作成功",
     "count": 1,
     "list": []
     */
    private int status;
    private String mes;
    private  int count;
    private List<GetShopActivityListRes> list;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<GetShopActivityListRes> getList() {
        return list;
    }

    public void setList(List<GetShopActivityListRes> list) {
        this.list = list;
    }

    public static class GetShopActivityListRes{
        /**
         *  "NOID": "4",
         "Title": "小活动",
         "Discount": "6折",
         "Remark": "小活动进行中"
         "Time": "2017-06-28 00:50"
         */
        private  int NOID;
        private String Title;
        private String Discount;
        private String Remark;
        private String Time;

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

        public String getTime() {
            return Time;
        }

        public void setTime(String time) {
            Time = time;
        }
    }


}
