package com.bbld.yxpt_sh.bean;

import java.util.List;

/**
 * 获取商户账户余额及收支记录
 * Created by dell on 2017/7/7.
 */

public class GetAccountMoneyLog {
    /**  "status": 0,
     "mes": "操作成功",
     "count": 6,
     "accountMoney": "98.00",
     "list": []
     */
    private int status;
    private String mes;
    private  int count;
    private String accountMoney;
    private List<GetAccountMoneyLogRes> list;

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

    public String getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(String accountMoney) {
        this.accountMoney = accountMoney;
    }

    public List<GetAccountMoneyLogRes> getList() {
        return list;
    }

    public void setList(List<GetAccountMoneyLogRes> list) {
        this.list = list;
    }

    public static class GetAccountMoneyLogRes{
        /**
         * "ID": 1,
         "Money": "21.00",
         "Remark": "00001201707051956278310000100008付款成功",
         "Time": "07-05 19:56"
         */
        private int ID;
        private String Money;
        private String Remark;
        private String Time;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getMoney() {
            return Money;
        }

        public void setMoney(String money) {
            Money = money;
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
