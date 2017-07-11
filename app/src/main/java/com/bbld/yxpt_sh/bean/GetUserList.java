package com.bbld.yxpt_sh.bean;

import java.util.List;

/**
 * Created by dell on 2017/7/1.
 * 获取商户会员列表
 */

public class GetUserList {
    /**
     "status": 0,
     "mes": "操作成功",
     "count": 1,
     "list": []
     */
    private int status;
    private String mes;
    private  int count;
    private List<GetUserListRes> list;

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

    public List<GetUserListRes> getList() {
        return list;
    }

    public void setList(List<GetUserListRes> list) {
        this.list = list;
    }

    public static class GetUserListRes{
        /**
         "NOID": "1",
         "NickName": " 张立武",
         "HeadPortrait": "http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIkqyiaicz1CIugP37VSJgZythN5wtrrFvR7NDkPicDER9frJAZialdn4NFcFnBVhiaNaop7lgLH5xutyg/0",
         "Mobile": "18510310218",
         "Remark": "",
         "OrderCount": "3",
         "OrderPrice": "4.00",
         "AddDate": "2017-06-20 01:47:33"
         }
         */
        private  int NOID;
        private String NickName;
        private String HeadPortrait;
        private String Mobile;
        private String Remark;
        private String OrderCount;
        private String OrderPrice;
        private String AddDate;

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

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String mobile) {
            Mobile = mobile;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String remark) {
            Remark = remark;
        }

        public String getOrderCount() {
            return OrderCount;
        }

        public void setOrderCount(String orderCount) {
            OrderCount = orderCount;
        }

        public String getOrderPrice() {
            return OrderPrice;
        }

        public void setOrderPrice(String orderPrice) {
            OrderPrice = orderPrice;
        }

        public String getAddDate() {
            return AddDate;
        }

        public void setAddDate(String addDate) {
            AddDate = addDate;
        }
    }

}
