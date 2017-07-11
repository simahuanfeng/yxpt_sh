package com.bbld.yxpt_sh.bean;

import java.util.List;

/**
 * 获取商户全部订单列表
 * Created by dell on 2017/7/1.
 */

public class GetShopOrderList {
    /**
     "status": 0,
     "mes": "操作成功",
     "count": 1,
     "total": "17.00",
     "list": []
     */
    private int status;
    private String mes;
    private  int count;
    private String total;
    private List<GetShopOrderListRes> list;

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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<GetShopOrderListRes> getList() {
        return list;
    }

    public void setList(List<GetShopOrderListRes> list) {
        this.list = list;
    }

    public static class GetShopOrderListRes{
        /**
         "NOID": "16",
         "NickName": " Toni",
         "HeadPortrait": "http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKI4b5WtMiarqAvedbIFldPCEfcaC4kTR0CV6mRo3wxJVa8CWYd09zFJGyRjyeLTKLr4iap3AKyNKCQ/0",
         "ActivityTitle": "活动",
         "OrderNo": "00001201706272130487530000100007",
         "ActivityDiscount": "0.80",
         "EnterAmount": "3.00",
         "AddDate": "2017-06-27 21:30:48"
         "IsReturn": 0,
         "ReturnStatus": ""
         */
        private  int NOID;
        private String NickName;
        private String HeadPortrait;
        private String ActivityTitle;
        private String OrderNo;
        private String ActivityDiscount;
        private String EnterAmount;
        private String AddDate;
        private int  IsReturn;
        private String  ReturnStatus;
        public int getNOID() {
            return NOID;
        }

        public void setNOID(int NOID) {
            this.NOID = NOID;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String nickName) {
            NickName = nickName;
        }

        public String getHeadPortrait() {
            return HeadPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            HeadPortrait = headPortrait;
        }

        public String getActivityTitle() {
            return ActivityTitle;
        }

        public void setActivityTitle(String activityTitle) {
            ActivityTitle = activityTitle;
        }

        public String getOrderNo() {
            return OrderNo;
        }

        public void setOrderNo(String orderNo) {
            OrderNo = orderNo;
        }

        public String getActivityDiscount() {
            return ActivityDiscount;
        }

        public void setActivityDiscount(String activityDiscount) {
            ActivityDiscount = activityDiscount;
        }

        public String getEnterAmount() {
            return EnterAmount;
        }

        public void setEnterAmount(String enterAmount) {
            EnterAmount = enterAmount;
        }

        public String getAddDate() {
            return AddDate;
        }

        public void setAddDate(String addDate) {
            AddDate = addDate;
        }

        public int getIsReturn() {
            return IsReturn;
        }

        public void setIsReturn(int isReturn) {
            IsReturn = isReturn;
        }

        public String getReturnStatus() {
            return ReturnStatus;
        }

        public void setReturnStatus(String returnStatus) {
            ReturnStatus = returnStatus;
        }
    }
}
